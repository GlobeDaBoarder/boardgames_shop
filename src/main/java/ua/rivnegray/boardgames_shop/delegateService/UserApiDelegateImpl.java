package ua.rivnegray.boardgames_shop.delegateService;

import generated.user.api.UsersApiDelegate;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAnyUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateCustomerUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateEmailDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateFullUserProfileDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePasswordDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePhoneDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateUsernameDto;
import ua.rivnegray.boardgames_shop.DTO.response.UserPublicDto;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserApiDelegateImpl implements UsersApiDelegate {
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return UsersApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> addAddress(Long userId, AddAndUpdateAddressDto addAndUpdateAddressDto) {
        return UsersApiDelegate.super.addAddress(userId, addAndUpdateAddressDto);
    }

    @Override
    public ResponseEntity<UserPublicDto> createCustomerUser(CreateCustomerUserDto createCustomerUserDto) {
        return UsersApiDelegate.super.createCustomerUser(createCustomerUserDto);
    }

    @Override
    public ResponseEntity<UserPublicDto> createSpecifiedUser(CreateAnyUserDto createAnyUserDto) {
        return UsersApiDelegate.super.createSpecifiedUser(createAnyUserDto);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        return UsersApiDelegate.super.deleteUser(userId);
    }

    @Override
    public ResponseEntity<List<UserPublicDto>> getAllUsersPublicInfo() {
        return UsersApiDelegate.super.getAllUsersPublicInfo();
    }

    @Override
    public ResponseEntity<UserPublicDto> getUserPublicInfoById(Long userId) {
        return UsersApiDelegate.super.getUserPublicInfoById(userId);
    }

    @Override
    public ResponseEntity<Void> removeAddress(Long userId, Long addressId) {
        return UsersApiDelegate.super.removeAddress(userId, addressId);
    }

    @Override
    public ResponseEntity<Void> updateAddress(Long userId, AddAndUpdateAddressDto addAndUpdateAddressDto) {
        return UsersApiDelegate.super.updateAddress(userId, addAndUpdateAddressDto);
    }

    @Override
    public ResponseEntity<Void> updateEmail(Long userId, UpdateEmailDto updateEmailDto) {
        return UsersApiDelegate.super.updateEmail(userId, updateEmailDto);
    }

    @Override
    public ResponseEntity<UserPublicDto> updateFullUserProfile(Long userId, UpdateFullUserProfileDto updateFullUserProfileDto) {
        return UsersApiDelegate.super.updateFullUserProfile(userId, updateFullUserProfileDto);
    }

    @Override
    public ResponseEntity<Void> updatePassword(Long userId, UpdatePasswordDto updatePasswordDto) {
        return UsersApiDelegate.super.updatePassword(userId, updatePasswordDto);
    }

    @Override
    public ResponseEntity<Void> updatePhone(Long userId, UpdatePhoneDto updatePhoneDto) {
        return UsersApiDelegate.super.updatePhone(userId, updatePhoneDto);
    }

    @Override
    public ResponseEntity<Void> updateUsername(Long userId, UpdateUsernameDto updateUsernameDto) {
        return UsersApiDelegate.super.updateUsername(userId, updateUsernameDto);
    }


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
}
