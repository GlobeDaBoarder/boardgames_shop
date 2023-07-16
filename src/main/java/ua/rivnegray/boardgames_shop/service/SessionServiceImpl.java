package ua.rivnegray.boardgames_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.LoginRequestDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateCustomerUserDto;
import ua.rivnegray.boardgames_shop.DTO.response.LoginResponseDto;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.UserIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.UsernameAlreadyTakenException;
import ua.rivnegray.boardgames_shop.mapper.UserMapper;
import ua.rivnegray.boardgames_shop.model.UserCredentials;
import ua.rivnegray.boardgames_shop.model.UserProfile;
import ua.rivnegray.boardgames_shop.repository.UserCredentialsRepository;
import ua.rivnegray.boardgames_shop.repository.UserProfileRepository;
import ua.rivnegray.boardgames_shop.repository.UserRoleRepository;

@Service
public class SessionServiceImpl implements SessionsService{
    UserProfileRepository userProfileRepository;
    UserCredentialsRepository userCredentialsRepository;

    TokenService tokenService;

    PasswordEncoder passwordEncoder;

    AuthenticationManager authenticationManager;

    UserMapper userMapper;

    UserRoleRepository userRoleRepository;

    @Autowired
    public SessionServiceImpl(UserProfileRepository userProfileRepository, UserCredentialsRepository userCredentialsRepository,
                              TokenService tokenService, PasswordEncoder passwordEncoder,
                              AuthenticationManager authenticationManager,
                              UserMapper userMapper, UserRoleRepository userRoleRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userCredentialsRepository = userCredentialsRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
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
        return new LoginResponseDto(this.userMapper.toUserPublicDto(userProfile), token);
    }

    @Override
    public LoginResponseDto register(CreateCustomerUserDto createCustomerUserDto) {
        if (userCredentialsRepository.existsByUsername(createCustomerUserDto.username())) {
            throw new UsernameAlreadyTakenException("Username already exists");
        }
        UserProfile userProfile = this.userMapper.toUserProfile(createCustomerUserDto, this.userRoleRepository);
        UserCredentials userCredentials = new UserCredentials(createCustomerUserDto.username(), passwordEncoder.encode(createCustomerUserDto.password()));
        userCredentials.setUserProfile(userProfile);
        userProfile.setUserCredentials(userCredentials);
        userProfileRepository.save(userProfile);
        String token = tokenService.generateToken(this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(createCustomerUserDto.username(), createCustomerUserDto.password())
        ));
        return new LoginResponseDto(this.userMapper.toUserPublicDto(userProfile), token);
    }
}
