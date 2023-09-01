package ua.rivnegray.boardgames_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.LoginRequestDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateCustomerUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.MapShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.LoginResponseDto;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.EmailAlreadyInUseException;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.PhoneAlreadyInUseException;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.TwoUserProfilesFoundException;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.UserAlreadyRegisteredException;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.UsernameAlreadyTakenException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.RoleNameNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.UserIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.UserMapper;
import ua.rivnegray.boardgames_shop.model.ProductInShoppingCart;
import ua.rivnegray.boardgames_shop.model.UserCredentials;
import ua.rivnegray.boardgames_shop.model.UserProfile;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;
import ua.rivnegray.boardgames_shop.repository.UserCredentialsRepository;
import ua.rivnegray.boardgames_shop.repository.UserProfileRepository;
import ua.rivnegray.boardgames_shop.repository.UserRoleRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionsService{
    UserProfileRepository userProfileRepository;
    UserCredentialsRepository userCredentialsRepository;

    TokenService tokenService;

    PasswordEncoder passwordEncoder;

    AuthenticationManager authenticationManager;

    UserMapper userMapper;

    UserRoleRepository userRoleRepository;

    BoardGameRepository boardGameRepository;

    @Autowired
    public SessionServiceImpl(UserProfileRepository userProfileRepository, UserCredentialsRepository userCredentialsRepository,
                              TokenService tokenService, PasswordEncoder passwordEncoder,
                              AuthenticationManager authenticationManager,
                              UserMapper userMapper, UserRoleRepository userRoleRepository,
                              BoardGameRepository boardGameRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userCredentialsRepository = userCredentialsRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.userRoleRepository = userRoleRepository;
        this.boardGameRepository = boardGameRepository;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto, MapShoppingCartDto mapShoppingCartDto) {
        UserCredentials userCredentials = this.userCredentialsRepository.findByUsername(loginRequestDto.username())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
        if (!this.passwordEncoder.matches(loginRequestDto.password(), userCredentials.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        UserProfile userProfile = userProfileRepository.findById(userCredentials.getId())
                .orElseThrow(() -> new UserIdNotFoundException(userCredentials.getId()));
        String token = tokenService.generateToken(this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password())
        ));

        mapShoppingCartDtoToUserProfileCart(mapShoppingCartDto, userProfile);

        this.userProfileRepository.save(userProfile);

        return new LoginResponseDto(this.userMapper.toUserPublicDto(userProfile), token);
    }

    @Override
    public LoginResponseDto register(CreateCustomerUserDto createCustomerUserDto, MapShoppingCartDto mapShoppingCartDto) {
        if (this.userCredentialsRepository.existsByUsername(createCustomerUserDto.username())) {
            throw new UsernameAlreadyTakenException(createCustomerUserDto.username());
        }

        Optional<UserProfile> userProfileFoundByEmailOptional = this.userProfileRepository.findByEmail(createCustomerUserDto.email());
        Optional<UserProfile> userProfileFoundByPhoneOptional = this.userProfileRepository.findByPhone(createCustomerUserDto.phone());

        UserProfile userProfile = null;

        if(userProfileFoundByEmailOptional.isPresent() && userProfileFoundByPhoneOptional.isPresent()){
            UserProfile userProfileFoundByEmail = userProfileFoundByEmailOptional.get();
            UserProfile userProfileFoundByPhone = userProfileFoundByPhoneOptional.get();
            if (!userProfileFoundByEmail.equals(userProfileFoundByPhone)){
                throw new TwoUserProfilesFoundException();
            }
            if (userProfileFoundByEmail.getUserCredentials() != null) {
                throw new UserAlreadyRegisteredException();
            }

            userProfile = userProfileFoundByEmail;
        }
        else if(userProfileFoundByEmailOptional.isPresent()){
            throw new EmailAlreadyInUseException(createCustomerUserDto.email());
        }
        else if(userProfileFoundByPhoneOptional.isPresent()){
            throw new PhoneAlreadyInUseException(createCustomerUserDto.phone());
        }
        else{
            userProfile = this.userMapper.toUserProfile(createCustomerUserDto, this.userRoleRepository);
        }

        UserCredentials userCredentials = new UserCredentials(createCustomerUserDto.username(), passwordEncoder.encode(createCustomerUserDto.password()));
        userCredentials.setUserProfile(userProfile);
        userProfile.setUserCredentials(userCredentials);

        mapShoppingCartDtoToUserProfileCart(mapShoppingCartDto, userProfile);
        userProfile.getRoles().add(this.userRoleRepository.findUserRoleByRoleName("ROLE_CUSTOMER")
                .orElseThrow(() -> new RoleNameNotFoundException("ROLE_CUSTOMER")));

        this.userProfileRepository.save(userProfile);

        String token = tokenService.generateToken(this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(createCustomerUserDto.username(), createCustomerUserDto.password())
        ));
        return new LoginResponseDto(this.userMapper.toUserPublicDto(userProfile), token);
    }

    private void mapShoppingCartDtoToUserProfileCart(MapShoppingCartDto mapShoppingCartDto, UserProfile userProfile) {
        userProfile.getShoppingCart().getProductsInShoppingCart().addAll(mapShoppingCartDto.simpleProductInShoppingCartDtos().stream()
                .map(simpleProductInShoppingCartDto -> ProductInShoppingCart.builder()
                        .product(this.boardGameRepository.findById(simpleProductInShoppingCartDto.productId())
                                .orElseThrow(() -> new BoardGameIdNotFoundException(simpleProductInShoppingCartDto.productId())))
                        .shoppingCart(userProfile.getShoppingCart())
                        .quantity(simpleProductInShoppingCartDto.quantity())
                        .build()
                )
                .collect(Collectors.toSet())
        );
    }
}
