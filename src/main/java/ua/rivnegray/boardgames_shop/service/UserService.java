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
import ua.rivnegray.boardgames_shop.DTO.response.UserRoleDto;

import java.util.List;

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
    UserPublicDto updateUsername(final UpdateUsernameDto updateUsernameDto);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    UserPublicDto updatePassword(final UpdatePasswordDto updatePasswordDto);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    UserPublicDto updateEmail(final UpdateEmailDto updateEmailDto);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    UserPublicDto updatePhone(final UpdatePhoneDto updatePhoneDto);

//    @Transactional(readOnly = true)
//    Boolean isEmailAvailable(UpdateEmailDto updateEmailDto);
//
//    @Transactional(readOnly = true)
//    Boolean isUsernameAvailable(UpdateUsernameDto updateUsernameDto);

    @Transactional(readOnly = true)
    AddressDto getAddress(Long addressId);

    @Transactional(readOnly = true)
    List<AddressDto> getAllAddresses();

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    UserPublicDto updateAddress(final Long addressId, final AddAndUpdateAddressDto updateAddressDto);

    @Transactional
    UserPublicDto addAddress(final AddAndUpdateAddressDto addAddressDto);

    @Transactional
    void removeAddress(final Long addressId);

    @Transactional
    void hardDeleteUser(Long id);

    @Transactional
    void deleteMe();

    @Transactional(readOnly = true)
    List<UserRoleDto> getAllUserRoles();

    @Transactional(readOnly = true)
    UserPublicDto getCurrentUserPublicInfo();
}
