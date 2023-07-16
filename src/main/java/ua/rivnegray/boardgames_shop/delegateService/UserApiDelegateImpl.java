package ua.rivnegray.boardgames_shop.delegateService;

import generated.user.api.UsersApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAnyUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateCustomerUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateEmailDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePasswordDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePhoneDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateUsernameDto;
import ua.rivnegray.boardgames_shop.DTO.response.AddressDto;
import ua.rivnegray.boardgames_shop.DTO.response.UserPublicDto;
import ua.rivnegray.boardgames_shop.DTO.response.UserRoleDto;
import ua.rivnegray.boardgames_shop.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserApiDelegateImpl implements UsersApiDelegate {
    UserService userService;

    @Autowired
    public UserApiDelegateImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return UsersApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<UserPublicDto> addAddress(AddAndUpdateAddressDto addAndUpdateAddressDto) {
        UserPublicDto userWithAddedAddress = this.userService.addAddress(addAndUpdateAddressDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userWithAddedAddress.id())
                .toUri();

        return ResponseEntity.created(location).body(userWithAddedAddress);
    }

    @Override
    public ResponseEntity<UserPublicDto> createSpecifiedUser(CreateAnyUserDto createAnyUserDto) {
        UserPublicDto createdUser = this.userService.createSpecifiedUser(createAnyUserDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.id())
                .toUri();

        return ResponseEntity.created(location).body(createdUser);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        this.userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<UserPublicDto>> getAllUsersPublicInfo() {
        return ResponseEntity.ok(this.userService.getAllUsersPublicInfo());
    }

    @Override
    public ResponseEntity<UserPublicDto> getUserPublicInfoById(Long userId) {
        return ResponseEntity.ok(this.userService.getUserPublicInfoById(userId));
    }

    @Override
    public ResponseEntity<UserPublicDto> getCurrentUserPublicInfo() {
        return ResponseEntity.ok(this.userService.getCurrentUserPublicInfo());
    }



    @Override
    public ResponseEntity<Void> removeAddress(Long addressId) {
        this.userService.removeAddress(addressId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserPublicDto> updateAddress(Long addressId,
                                                       AddAndUpdateAddressDto addAndUpdateAddressDto) {
        return ResponseEntity.ok(this.userService.updateAddress(addressId, addAndUpdateAddressDto));
    }

    @Override
    public ResponseEntity<UserPublicDto> updateEmail(UpdateEmailDto updateEmailDto) {
        return ResponseEntity.ok(this.userService.updateEmail(updateEmailDto));
    }

    @Override
    public ResponseEntity<UserPublicDto> updatePassword(UpdatePasswordDto updatePasswordDto) {
        return ResponseEntity.ok(this.userService.updatePassword(updatePasswordDto));
    }

    @Override
    public ResponseEntity<UserPublicDto> updatePhone(UpdatePhoneDto updatePhoneDto) {
        return ResponseEntity.ok(this.userService.updatePhone(updatePhoneDto));
    }

    @Override
    public ResponseEntity<UserPublicDto> updateUsername(UpdateUsernameDto updateUsernameDto) {
        return ResponseEntity.ok(this.userService.updateUsername(updateUsernameDto));
    }

//    @Override
//    public ResponseEntity<Boolean> checkEmailAvailability(UpdateEmailDto updateEmailDto) {
//        if (this.userService.isEmailAvailable(updateEmailDto)) {
//            return new ResponseEntity<>(true, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
//        }
//    }
//
//    @Override
//    public ResponseEntity<Boolean> checkUsernameAvailability(UpdateUsernameDto updateUsernameDto) {
//        if (this.userService.isUsernameAvailable(updateUsernameDto)) {
//            return new ResponseEntity<>(true, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
//        }
//    }

    @Override
    public ResponseEntity<AddressDto> getAddressById(Long addressId) {
        return ResponseEntity.ok(this.userService.getAddress(addressId));
    }

    @Override
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        return ResponseEntity.ok(this.userService.getAllAddresses());
    }

    @Override
    public ResponseEntity<List<UserPublicDto>> getUsersByRole(String role) {
        return ResponseEntity.ok(this.userService.getUsersPublicInfoByRole(role));
    }

    @Override
    public ResponseEntity<List<UserRoleDto>> getAllUserRoles() {
        return ResponseEntity.ok(this.userService.getAllUserRoles());
    }
}
