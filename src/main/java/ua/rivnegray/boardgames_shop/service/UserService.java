package ua.rivnegray.boardgames_shop.service;

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

public interface UserService {
    List<UserPublicDto> getAllUsersPublicInfo();
    Optional<UserPublicDto> getUserPublicInfoById(Long id);

    UserPublicDto createSpecifiedUser(CreateAnyUserDto user);

    UserPublicDto createCustomerUser(CreateCustomerUserDto user);

    UserPublicDto updateFullUserProfile(final UpdateFullUserProfileDto updateFullUserProfileDto);

    void updateUsername(final UpdateUsernameDto updateUsernameDto);

    void updatePassword(final UpdatePasswordDto updatePasswordDto);

    void updateEmail(final UpdateEmailDto updateEmailDto);

    void updatePhone(final UpdatePhoneDto updatePhoneDto);

    void updateAddress(final AddAndUpdateAddressDto updateAddressDto);

    void addAddress(final AddAndUpdateAddressDto addAddressDto);

    void removeAddress(final Long userId, final Long addressId);
    void deleteUser(Long id);
}
