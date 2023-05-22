package ua.rivnegray.boardgames_shop.delegateService;

import generated.user.api.UsersApiDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rivnegray.boardgames_shop.DTO.request.CreateAndUpdateUserDto;
import ua.rivnegray.boardgames_shop.DTO.response.UserDto;
import ua.rivnegray.boardgames_shop.mapper.UserMapper;
import ua.rivnegray.boardgames_shop.model.User;
import ua.rivnegray.boardgames_shop.service.UserRoleService;
import ua.rivnegray.boardgames_shop.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserApiDelegateImpl implements UsersApiDelegate {
    UserService userService;
    UserMapper userMapper;

    UserRoleService roleService;

    @Autowired
    public UserApiDelegateImpl(UserService userService, UserMapper userMapper, UserRoleService roleService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }


    @Override
    public ResponseEntity<UserDto> createUser(CreateAndUpdateUserDto createAndUpdateUserDto) {
        User tempUser = this.userMapper.createAndUpdateUserDtoToUser(createAndUpdateUserDto, this.roleService);
        User persistedUser = this.userService.createUser(tempUser);
        UserDto persistedUserDto = this.userMapper.userToUserDto(persistedUser);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(persistedUserDto.id())
                .toUri();


        return ResponseEntity.created(location).body(persistedUserDto);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        this.userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers()
                .stream().map(userMapper::userToUserDto).toList());
    }

    @Override
    public ResponseEntity<UserDto> getUserById(Long id) {
        return this.userService.getUserById(id).map(user -> ResponseEntity.ok(this.userMapper.userToUserDto(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Long id, CreateAndUpdateUserDto createAndUpdateUserDto) {
        User infoUserToUpdate = this.userMapper.createAndUpdateUserDtoToUser(createAndUpdateUserDto, this.roleService);
        infoUserToUpdate.setId(id);

        User updatedUser = this.userService.updateUser(infoUserToUpdate);
        UserDto updatedUserDto = this.userMapper.userToUserDto(updatedUser);
        return ResponseEntity.ok(updatedUserDto);
    }
}
