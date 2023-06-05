package ua.rivnegray.boardgames_shop.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
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

import java.util.List;
import java.util.Optional;

public interface UserService {

    @Transactional(readOnly = true)
    List<UserPublicDto> getAllUsersPublicInfo();

    @Transactional(readOnly = true)
    UserPublicDto getUserPublicInfoById(Long id);

    @Transactional(readOnly = true)
    List<UserPublicDto> getUsersPublicInfoByRole(String role);

    @Transactional
    UserPublicDto createSpecifiedUser(CreateAnyUserDto createAnyUserDto);

    @Transactional
    UserPublicDto createCustomerUser(CreateCustomerUserDto createCustomerUserDto);

    @Transactional
    UserPublicDto updateUsername(final Long id, final UpdateUsernameDto updateUsernameDto);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    UserPublicDto updatePassword(final Long id, final UpdatePasswordDto updatePasswordDto);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    UserPublicDto updateEmail(final Long id, final UpdateEmailDto updateEmailDto);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    UserPublicDto updatePhone(final Long id, final UpdatePhoneDto updatePhoneDto);

    @Transactional(readOnly = true)
    Boolean isEmailAvailable(UpdateEmailDto updateEmailDto);

    @Transactional(readOnly = true)
    Boolean isUsernameAvailable(UpdateUsernameDto updateUsernameDto);

    @Transactional(readOnly = true)
    AddressDto getAddress(Long userId, Long addressId);

    @Transactional(readOnly = true)
    List<AddressDto> getAllAddresses(Long userId);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    UserPublicDto updateAddress(final Long userId, final Long addressId, final AddAndUpdateAddressDto updateAddressDto);

    @Transactional
    UserPublicDto addAddress(final Long userId, final AddAndUpdateAddressDto addAddressDto);

    @Transactional
    void removeAddress(final Long userId, final Long addressId);

    @Transactional
    void deleteUser(Long id);
}
