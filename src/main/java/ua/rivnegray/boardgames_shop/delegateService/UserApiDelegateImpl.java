package ua.rivnegray.boardgames_shop.delegateService;

import generated.user.api.UsersApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
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
import ua.rivnegray.boardgames_shop.model.Address;
import ua.rivnegray.boardgames_shop.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserApiDelegateImpl implements UsersApiDelegate {

    // todo change return types for update methods from void to DTOs
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
    public ResponseEntity<AddressDto> addAddress(Long userId, AddAndUpdateAddressDto addAndUpdateAddressDto) {
        AddressDto address = this.userService.addAddress(userId, addAndUpdateAddressDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(address.id())
                .toUri();

        return ResponseEntity.created(location).body(address);
    }

    @Override
    public ResponseEntity<UserPublicDto> createCustomerUser(CreateCustomerUserDto createCustomerUserDto) {

        UserPublicDto createdUser = this.userService.createCustomerUser(createCustomerUserDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.id())
                .toUri();

        return ResponseEntity.created(location).body(createdUser);
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
    public ResponseEntity<Void> removeAddress(Long userId, Long addressId) {
        this.userService.removeAddress(userId, addressId);
        return ResponseEntity.noContent().build();
    }

//    @Override
//    public ResponseEntity<AddressDto> updateAddress(Long userId, Long addressId, AddAndUpdateAddressDto addAndUpdateAddressDto) {
//        return ResponseEntity.ok(this.userService.updateAddress(userId, addressId, addAndUpdateAddressDto));
//    }

    @Override
    public ResponseEntity<UserPublicDto> updateEmail(Long userId, UpdateEmailDto updateEmailDto) {
        return ResponseEntity.ok(this.userService.updateEmail(userId, updateEmailDto));
    }

    @Override
    public ResponseEntity<UserPublicDto> updatePassword(Long userId, UpdatePasswordDto updatePasswordDto) {
        return ResponseEntity.ok(this.userService.updatePassword(userId, updatePasswordDto));
    }

    @Override
    public ResponseEntity<UserPublicDto> updatePhone(Long userId, UpdatePhoneDto updatePhoneDto) {
        return ResponseEntity.ok(this.userService.updatePhone(userId, updatePhoneDto));
    }

    @Override
    public ResponseEntity<UserPublicDto> updateUsername(Long userId, UpdateUsernameDto updateUsernameDto) {
        return ResponseEntity.ok(this.userService.updateUsername(userId, updateUsernameDto));
    }
}
