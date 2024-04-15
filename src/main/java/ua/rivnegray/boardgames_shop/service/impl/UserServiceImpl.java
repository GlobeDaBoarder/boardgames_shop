package ua.rivnegray.boardgames_shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAnyUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.MapProductInFavouritesDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateEmailDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateNameAndSurnameDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePasswordDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePhoneDto;
import ua.rivnegray.boardgames_shop.DTO.response.*;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.FavouriteProductAlreadyExistsException;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.PasswordMissMatchException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.*;
import ua.rivnegray.boardgames_shop.mapper.BoardGameMapperService;
import ua.rivnegray.boardgames_shop.mapper.FavouriteProductMapper;
import ua.rivnegray.boardgames_shop.mapper.UserMapper;
import ua.rivnegray.boardgames_shop.model.*;
import ua.rivnegray.boardgames_shop.repository.*;
import ua.rivnegray.boardgames_shop.service.TokenExtractorService;
import ua.rivnegray.boardgames_shop.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final AddressRepository userAddressRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final BoardGameRepository boardGameRepository;
    private final FavouriteProductRepository favouriteProductRepository;
    private final FavouriteProductMapper favouriteProductMapper;
    private final BoardGameMapperService boardGameMapper;
    private final TokenExtractorService tokenExtractorService;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
        String email = jwtPrincipal.getSubject();
        Long id = jwtPrincipal.getClaim("id");

        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public List<UserPublicDto> getAllUsersPublicInfo() {
        return this.userRepository.findAll().stream()
                .map(this.userMapper::toUserPublicDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public UserPublicDto getUserPublicInfoById(Long id) {
        return this.userRepository.findById(id)
                .map(this.userMapper::toUserPublicDto)
                .orElseThrow(() -> new UserIdNotFoundException(id));
    }

    @Override
    public UserPublicDto createSpecifiedUser(CreateAnyUserDto createAnyUserDto) {
        User user = User.builder()
                .email(createAnyUserDto.email())
                .password(this.passwordEncoder.encode(createAnyUserDto.password()))
                .phone(createAnyUserDto.phone())
                .firstName(createAnyUserDto.firstName())
                .lastName(createAnyUserDto.lastName())
                .nickname(createAnyUserDto.nickName())
                .registrationStatus(UserRegistrationStatus.REGISTERED)
                .roles(this.userRoleRepository.findAllById(createAnyUserDto.roleIds()))
                .build();

        return this.userMapper.toUserPublicDto(this.userRepository.save(user));
    }

    @Override
    public UserPublicDto updateMyPassword(UpdatePasswordDto updatePasswordDto) {
        User userToUpdate = this.getCurrentUser();

        // todo verify email
        if(!passwordEncoder.matches(updatePasswordDto.oldPassword(), userToUpdate.getPassword()))
            throw new PasswordMissMatchException();

        userToUpdate.setPassword(this.passwordEncoder.encode(updatePasswordDto.newPassword()));
        return this.userMapper.toUserPublicDto(this.userRepository.save(userToUpdate));

    }

    @Override
    public UserPublicDto updateMyEmail(UpdateEmailDto updateEmailDto) {
        User userToUpdate = this.getCurrentUser();

        // todo verify email
        userToUpdate.setEmail(updateEmailDto.email());
        return this.userMapper.toUserPublicDto(this.userRepository.save(userToUpdate));

    }

    @Override
    public UserPublicDto updateMyPhone(UpdatePhoneDto updatePhoneDto) {
        User userToUpdate = this.getCurrentUser();

        // todo verify phone number
        userToUpdate.setPhone(updatePhoneDto.phone());
        return this.userMapper.toUserPublicDto(this.userRepository.save(userToUpdate));

    }

    @Override
    public UserPublicDto updateMyAddress(Long addressId, AddAndUpdateAddressDto updateAddressDto) {
        User userToUpdate = this.getCurrentUser();

        Address addressToUpdate = userToUpdate.getAddresses().stream()
                .filter(address -> address.getId().equals(addressId))
                .findFirst()
                .orElseThrow(() -> new AddressIdNotFoundException(addressId));

        this.userMapper.updateAddress(addressToUpdate, updateAddressDto);

        return this.userMapper.toUserPublicDto(this.userRepository.save(userToUpdate));

    }

    @Override
    public UserPublicDto addMyAddress(AddAndUpdateAddressDto addAddressDto) {
        User userToUpdate = this.getCurrentUser();

        Address addressToAdd = this.userMapper.toAddress(addAddressDto);
        userToUpdate.getAddresses().add(addressToAdd);
        return this.userMapper.toUserPublicDto(this.userRepository.save(userToUpdate));
    }

    @Override
    public void removeMyAddress(Long addressId) {
        User userToUpdate = this.getCurrentUser();

        userToUpdate.getAddresses().removeIf(address -> address.getId().equals(addressId));
        this.userRepository.save(userToUpdate);

    }

    @Override
    public void hardDeleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public void deleteMe() {
        User userToDelete = this.getCurrentUser();
        userToDelete.setIsRemoved(true);
        this.userRepository.save(userToDelete);
    }

    @Override
    public List<UserRoleDto> getAllUserRoles() {
        return this.userRoleRepository.findAll().stream()
                .map(this.userMapper::toUserRoleDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public UserPublicDto getMyUserInfo() {
        return this.userMapper.toUserPublicDto(getCurrentUser());
    }

    @Override
    public List<UserPublicDto> getUsersPublicInfoByRole(String role) {
        return this.userRepository.findByRoles_RoleName(role).stream()
                .map(this.userMapper::toUserPublicDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public AddressDto getMyAddressById(Long addressId) {
        return this.userMapper.toAddressDto(this.userAddressRepository.findById(addressId)
                .orElseThrow(() -> new AddressIdNotFoundException(addressId)));
    }

    @Override
    public List<AddressDto> getAllMyAddresses() {
        User userProfileTiGetAddressesFrom = this.getCurrentUser();

        return userProfileTiGetAddressesFrom.getAddresses().stream()
                .map(this.userMapper::toAddressDto)
                .collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public UserPublicDto updateMyNameAndSurname(UpdateNameAndSurnameDto updateNameAndSurnameDto) {
        User userToUpdate = this.getCurrentUser();

        userToUpdate.setFirstName(updateNameAndSurnameDto.nameAndSurname().trim().split(" ")[0]);
        userToUpdate.setLastName(updateNameAndSurnameDto.nameAndSurname().trim().split(" ")[1]);

        return this.userMapper.toUserPublicDto(this.userRepository.save(userToUpdate));
    }

    @Override
    public FavouriteProductCreationResponseDto addProductToFavourites(Long productId) {
        BoardGame boardGame = this.boardGameRepository.findById(productId)
                .orElseThrow(() -> new BoardGameIdNotFoundException(productId));

        User me = this.getCurrentUser();

        if(this.favouriteProductRepository.existsByUserIdAndBoardGameId(me.getId(), productId))
            throw new FavouriteProductAlreadyExistsException(productId);

        return this.favouriteProductMapper.toFavouriteProductCreationResponseDto(this.favouriteProductRepository.save(FavouriteProduct.builder()
                .user(me)
                .boardGame(boardGame)
                .build()));
    }

    @Override
    public List<FavouriteProductDto> getAllMyFavouriteProducts() {
        return this.favouriteProductRepository.findAllByUserId(this.getCurrentUser().getId()).stream()
                .map(this.favouriteProductMapper::toFavouriteProductDto)
                .toList();

    }

    @Override
    public void removeProductFromFavourites(Long favoriteId) {
        if(!this.favouriteProductRepository.existsById(favoriteId))
            throw new FavouriteProductNotFoundException(favoriteId);

        this.favouriteProductRepository.deleteById(favoriteId);
    }

    @Override
    public void removeAllMyFavouriteProducts() {
        this.favouriteProductRepository.deleteAllByUserId(this.getCurrentUser().getId());
    }

    @Override
    public void mapFavourites(List<MapProductInFavouritesDto> productInFavouritesDtos) {
        Set<Long> favouritesIds = getCurrentUserFavourites()
                .stream().map(FavouriteProduct::getBoardGame)
                .map(BaseEntity::getId)
                .collect(Collectors.toUnmodifiableSet());

        List<FavouriteProduct> newFavourites = productInFavouritesDtos.stream()
                .map(MapProductInFavouritesDto::productId)
                .filter(id -> !favouritesIds.contains(id))
                .map(boardGameRepository::findById)
                .flatMap(Optional::stream)
                .map(boardGame -> FavouriteProduct.builder()
                        .boardGame(boardGame)
                        .user(tokenExtractorService.extractCurrentUser())
                        .build())
                .toList();

        favouriteProductRepository.saveAll(newFavourites);
    }

    private List<FavouriteProduct> getCurrentUserFavourites() {
        return favouriteProductRepository.findAllByUserId(tokenExtractorService.extractCurrentUserId());
    }
}
