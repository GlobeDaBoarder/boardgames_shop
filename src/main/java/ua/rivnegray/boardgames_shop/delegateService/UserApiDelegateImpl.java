package ua.rivnegray.boardgames_shop.delegateService;

import generated.user.api.UsersApiDelegate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAnyUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.MapProductInFavouritesDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateEmailDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateNameAndSurnameDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePasswordDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePhoneDto;
import ua.rivnegray.boardgames_shop.DTO.response.*;
import ua.rivnegray.boardgames_shop.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class UserApiDelegateImpl implements UsersApiDelegate {
    private final UserService userService;

    @Autowired
    UserApiDelegateImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return UsersApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<UserPublicDto> addAddress(AddAndUpdateAddressDto addAndUpdateAddressDto) {
        UserPublicDto userWithAddedAddress = this.userService.addMyAddress(addAndUpdateAddressDto);

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
    public ResponseEntity<Void> deleteMe() {
        this.userService.deleteMe();
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> hardDeleteUser(Long userId) {
        this.userService.hardDeleteUser(userId);
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
    public ResponseEntity<UserPublicDto> getMe() {
        return ResponseEntity.ok(this.userService.getMyUserInfo());
    }

    @Override
    public ResponseEntity<Void> removeAddress(Long addressId) {
        this.userService.removeMyAddress(addressId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserPublicDto> updateMyAddress(Long addressId,
                                                       AddAndUpdateAddressDto addAndUpdateAddressDto) {
        return ResponseEntity.ok(this.userService.updateMyAddress(addressId, addAndUpdateAddressDto));
    }

    @Override
    public ResponseEntity<UserPublicDto> updateMyEmail(UpdateEmailDto updateEmailDto) {
        return ResponseEntity.ok(this.userService.updateMyEmail(updateEmailDto));
    }

    @Override
    public ResponseEntity<UserPublicDto> updateMyPassword(UpdatePasswordDto updatePasswordDto) {
        return ResponseEntity.ok(this.userService.updateMyPassword(updatePasswordDto));
    }

    @Override
    public ResponseEntity<UserPublicDto> updateMyPhone(UpdatePhoneDto updatePhoneDto) {
        return ResponseEntity.ok(this.userService.updateMyPhone(updatePhoneDto));
    }


    @Override
    public ResponseEntity<AddressDto> getMyAddressById(Long addressId) {
        return ResponseEntity.ok(this.userService.getMyAddressById(addressId));
    }

    @Override
    public ResponseEntity<List<AddressDto>> getAllMyAddresses() {
        return ResponseEntity.ok(this.userService.getAllMyAddresses());
    }

    @Override
    public ResponseEntity<List<UserPublicDto>> getUsersByRole(String role) {
        return ResponseEntity.ok(this.userService.getUsersPublicInfoByRole(role));
    }

    @Override
    public ResponseEntity<List<UserRoleDto>> getAllUserRoles() {
        return ResponseEntity.ok(this.userService.getAllUserRoles());
    }

    @Override
    public ResponseEntity<UserPublicDto> updateMyNameAndSurname(UpdateNameAndSurnameDto updateNameAndSurnameDto) {
        return ResponseEntity.ok(this.userService.updateMyNameAndSurname(updateNameAndSurnameDto));
    }

    @Override
    public ResponseEntity<FavouriteProductCreationResponseDto> addProductToFavourites(Long productId) {
        FavouriteProductCreationResponseDto favouriteProductCreationResponseDto = this.userService.addProductToFavourites(productId);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(favouriteProductCreationResponseDto.id())
                .toUri();

        return ResponseEntity.created(location).body(favouriteProductCreationResponseDto);
    }

    @Override
    public ResponseEntity<List<FavouriteProductDto>> getAllMyFavouriteProducts() {
        return ResponseEntity.ok(this.userService.getAllMyFavouriteProducts());
    }

    @Override
    public ResponseEntity<Void> removeProductFromFavourites(Long favoriteId) {
        this.userService.removeProductFromFavourites(favoriteId);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> removeAllMyFavouriteProducts() {
        this.userService.removeAllMyFavouriteProducts();

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> mapFavourites(List<@Valid MapProductInFavouritesDto> mapProductInFavouritesDto) {
        userService.mapFavourites(mapProductInFavouritesDto);
        return ResponseEntity.ok().build();
    }
}
