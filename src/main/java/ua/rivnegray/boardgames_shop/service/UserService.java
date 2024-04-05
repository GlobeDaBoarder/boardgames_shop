package ua.rivnegray.boardgames_shop.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAnyUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateEmailDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateNameAndSurnameDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePasswordDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePhoneDto;
import ua.rivnegray.boardgames_shop.DTO.response.AddressDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameSummaryDto;
import ua.rivnegray.boardgames_shop.DTO.response.FavouriteProductCreationResponseDto;
import ua.rivnegray.boardgames_shop.DTO.response.FavouriteProductDto;
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

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    UserPublicDto updateMyPassword(final UpdatePasswordDto updatePasswordDto);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    UserPublicDto updateMyEmail(final UpdateEmailDto updateEmailDto);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    UserPublicDto updateMyPhone(final UpdatePhoneDto updatePhoneDto);

    @Transactional(readOnly = true)
    AddressDto getMyAddressById(Long addressId);

    @Transactional(readOnly = true)
    List<AddressDto> getAllMyAddresses();

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    UserPublicDto updateMyAddress(final Long addressId, final AddAndUpdateAddressDto updateAddressDto);

    @Transactional
    UserPublicDto addMyAddress(final AddAndUpdateAddressDto addAddressDto);

    @Transactional
    void removeMyAddress(final Long addressId);

    @Transactional
    void hardDeleteUser(Long id);

    @Transactional
    void deleteMe();

    @Transactional(readOnly = true)
    List<UserRoleDto> getAllUserRoles();

    @Transactional(readOnly = true)
    UserPublicDto getMyUserInfo();

    @Transactional
    UserPublicDto updateMyNameAndSurname(UpdateNameAndSurnameDto updateNameAndSurnameDto);

    @Transactional
    FavouriteProductCreationResponseDto addProductToFavourites(Long productId);

    @Transactional(readOnly = true)
    List<FavouriteProductDto> getAllMyFavouriteProducts();

    @Transactional
    void removeProductFromFavourites(Long favoriteId);

    @Transactional
    void removeAllMyFavouriteProducts();
}
