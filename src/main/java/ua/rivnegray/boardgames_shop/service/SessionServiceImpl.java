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
import ua.rivnegray.boardgames_shop.DTO.response.IntermediateRegisterResponseDto;
import ua.rivnegray.boardgames_shop.DTO.response.TokenDto;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.UserAlreadyRegisteredException;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.UsernameAlreadyTakenException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.RoleNameNotFoundException;
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
    private final UserProfileRepository userProfileRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final UserRoleRepository userRoleRepository;
    private final BoardGameRepository boardGameRepository;

    @Autowired
    SessionServiceImpl(UserProfileRepository userProfileRepository, UserCredentialsRepository userCredentialsRepository,
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
    public TokenDto login(LoginRequestDto loginRequestDto) {
        UserCredentials userCredentials = this.userCredentialsRepository.findByUsername(loginRequestDto.username())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
        if (!this.passwordEncoder.matches(loginRequestDto.password(), userCredentials.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return new TokenDto(tokenService.generateToken(this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password())
        )));
    }

    @Override
    public IntermediateRegisterResponseDto register(CreateCustomerUserDto createCustomerUserDto) {
        if (this.userCredentialsRepository.existsByUsername(createCustomerUserDto.username())) {
            throw new UsernameAlreadyTakenException(createCustomerUserDto.username());
        }

        UserProfile userProfile = findOrCreateUserProfile(createCustomerUserDto);
        Long userid = userProfile.getId();

        UserCredentials userCredentials = new UserCredentials(createCustomerUserDto.username(), passwordEncoder.encode(createCustomerUserDto.password()));
        userCredentials.setUserProfile(userProfile);
        userProfile.setUserCredentials(userCredentials);

        this.userProfileRepository.save(userProfile);

        String token =  tokenService.generateToken(this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(createCustomerUserDto.username(), createCustomerUserDto.password())
        ));

        return IntermediateRegisterResponseDto.builder()
                .token(new TokenDto(token))
                .userId(userid)
                .build();
    }

    private UserProfile findOrCreateUserProfile(CreateCustomerUserDto createCustomerUserDto) {
        Optional<UserProfile> userProfileFoundByEmail = this.userProfileRepository.findByEmail(createCustomerUserDto.email());
        if(userProfileFoundByEmail.isPresent()){
            UserProfile existingProfile = userProfileFoundByEmail.get();
            if(existingProfile.getUserCredentials() != null) {
                throw new UserAlreadyRegisteredException();
            }
            this.userMapper.updateUserProfile(existingProfile, createCustomerUserDto);

            existingProfile.getRoles().add(this.userRoleRepository.findUserRoleByRoleName("ROLE_CUSTOMER")
                    .orElseThrow(() -> new RoleNameNotFoundException("ROLE_CUSTOMER")));

            return existingProfile;
        }

        return this.userMapper.toUserProfile(createCustomerUserDto, this.userRoleRepository);
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
