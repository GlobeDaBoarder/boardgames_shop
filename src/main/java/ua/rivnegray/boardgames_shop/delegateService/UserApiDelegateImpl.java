//package ua.rivnegray.boardgames_shop.delegateService;
//
//import generated.user.api.UsersApiDelegate;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import ua.rivnegray.boardgames_shop.DTO.request.CreateAndUpdateUserDto;
//import ua.rivnegray.boardgames_shop.DTO.response.UserFullDto;
//import ua.rivnegray.boardgames_shop.mapper.UserMapper;
//import ua.rivnegray.boardgames_shop.model.User;
//import ua.rivnegray.boardgames_shop.service.UserRoleService;
//import ua.rivnegray.boardgames_shop.service.UserService;
//
//import java.net.URI;
//import java.util.List;
//
//@Service
//@Primary
//public class UserApiDelegateImpl implements UsersApiDelegate {
//    UserService userService;
//    UserMapper userMapper;
//
//    UserRoleService roleService;
//
//    @Autowired
//    public UserApiDelegateImpl(UserService userService, UserMapper userMapper, UserRoleService roleService) {
//        this.userService = userService;
//        this.userMapper = userMapper;
//        this.roleService = roleService;
//    }
//
//
//    @Override
//    public ResponseEntity<UserFullDto> createUser(CreateAndUpdateUserDto createAndUpdateUserDto) {
//        User tempUser = this.userMapper.createAndUpdateUserDtoToUser(createAndUpdateUserDto, this.roleService);
//        User persistedUser = this.userService.createUser(tempUser);
//        UserFullDto persistedUserFullDto = this.userMapper.userToUserDto(persistedUser);
//
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(persistedUserFullDto.id())
//                .toUri();
//
//
//        return ResponseEntity.created(location).body(persistedUserFullDto);
//    }
//
//    @Override
//    public ResponseEntity<Void> deleteUser(Long id) {
//        this.userService.deleteUser(id);
//
//        return ResponseEntity.noContent().build();
//    }
//
//    @Override
//    public ResponseEntity<List<UserFullDto>> getAllUsers() {
//        return ResponseEntity.ok(this.userService.getAllSecureUsers()
//                .stream().map(userMapper::userToUserDto).toList());
//    }
//
//    @Override
//    public ResponseEntity<UserFullDto> getUserById(Long id) {
//        return this.userService.getSecureUserById(id).map(user -> ResponseEntity.ok(this.userMapper.userToUserDto(user)))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @Override
//    public ResponseEntity<UserFullDto> updateUser(Long id, CreateAndUpdateUserDto createAndUpdateUserDto) {
//        User infoUserToUpdate = this.userMapper.createAndUpdateUserDtoToUser(createAndUpdateUserDto, this.roleService);
//        infoUserToUpdate.setId(id);
//
//        User updatedUser = this.userService.updateUser(infoUserToUpdate);
//        UserFullDto updatedUserFullDto = this.userMapper.userToUserDto(updatedUser);
//        return ResponseEntity.ok(updatedUserFullDto);
//    }
//}
