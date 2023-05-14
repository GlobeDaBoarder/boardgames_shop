package ua.rivnegray.boardgames_shop.delegateService;

import generated.user.api.UsersApiDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rivnegray.boardgames_shop.DTO.UserDto;
import ua.rivnegray.boardgames_shop.mapper.UserMapper;
import ua.rivnegray.boardgames_shop.model.user.User;
import ua.rivnegray.boardgames_shop.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserApiDelegateImpl implements UsersApiDelegate {
    UserService userService;
    UserMapper userMapper;

    @Autowired
    public UserApiDelegateImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        User tempUser = this.userMapper.userDtoToUser(userDto);
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
    public ResponseEntity<UserDto> updateUser(Long id, UserDto userDto) {
        User updateInfoUser = this.userMapper.userDtoToUser(userDto);
        Optional<User> updatedUser = this.userService.updateUser(id, updateInfoUser);
        if(updatedUser.isPresent()){
            UserDto updatedUserDto = this.userMapper.userToUserDto(updatedUser.get());
            return ResponseEntity.ok(updatedUserDto);
        }
        return ResponseEntity.notFound().build();
    }
}
