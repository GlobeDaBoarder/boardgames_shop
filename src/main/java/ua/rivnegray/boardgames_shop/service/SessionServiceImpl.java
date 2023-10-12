package ua.rivnegray.boardgames_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.LoginRequestDto;
import ua.rivnegray.boardgames_shop.DTO.request.RegisterCustomerRequestDto;
import ua.rivnegray.boardgames_shop.DTO.response.IntermediateRegisterResponseDto;
import ua.rivnegray.boardgames_shop.DTO.response.TokenDto;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.UserAlreadyRegisteredException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.RoleNameNotFoundException;
import ua.rivnegray.boardgames_shop.model.User;
import ua.rivnegray.boardgames_shop.model.UserRegistrationStatus;
import ua.rivnegray.boardgames_shop.repository.UserRepository;
import ua.rivnegray.boardgames_shop.repository.UserRoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionsService{
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRoleRepository userRoleRepository;

    @Override
    public TokenDto login(LoginRequestDto loginRequestDto) {
        User user = this.userRepository.findByEmail(loginRequestDto.email())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
        if (user.getRegistrationStatus().equals(UserRegistrationStatus.NOT_REGISTERED))
            throw new BadCredentialsException("You are not registered yet to login. Please register first");
        if (!this.passwordEncoder.matches(loginRequestDto.password(), user.getPassword()))
            throw new BadCredentialsException("Invalid username or password");

        return new TokenDto(tokenService.generateToken(this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.email(), loginRequestDto.password())
        )));
    }

    @Override
    public IntermediateRegisterResponseDto register(RegisterCustomerRequestDto registerCustomerRequestDto) {
        Optional<User> userOptional = this.userRepository.findByEmail(registerCustomerRequestDto.email());

        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(user.getRegistrationStatus().equals(UserRegistrationStatus.REGISTERED)) {
                throw new UserAlreadyRegisteredException();
            }

            // todo email verification
            user = user.toBuilder()
                    .password(this.passwordEncoder.encode(registerCustomerRequestDto.password()))
                    .phone(registerCustomerRequestDto.phone())
                    .firstName(registerCustomerRequestDto.firstName())
                    .lastName(registerCustomerRequestDto.lastName())
                    .registrationStatus(UserRegistrationStatus.REGISTERED)
                    .role(this.userRoleRepository.findCustomerRole().orElseThrow(() -> new RoleNameNotFoundException("ROLE_CUSTOMER")))
                    .build();

            this.userRepository.save(user);

            return createIntermediateRegistrationResponse(user, registerCustomerRequestDto);
        }

        User user = User.builder()
                .email(registerCustomerRequestDto.email())
                .password(this.passwordEncoder.encode(registerCustomerRequestDto.password()))
                .phone(registerCustomerRequestDto.phone())
                .firstName(registerCustomerRequestDto.firstName())
                .lastName(registerCustomerRequestDto.lastName())
                .registrationStatus(UserRegistrationStatus.REGISTERED)
                .role(this.userRoleRepository.findCustomerRole().orElseThrow(() -> new RoleNameNotFoundException("ROLE_CUSTOMER")))
                .build();

        this.userRepository.save(user);

        return createIntermediateRegistrationResponse(user, registerCustomerRequestDto);
    }

    private IntermediateRegisterResponseDto createIntermediateRegistrationResponse(User user, RegisterCustomerRequestDto registerCustomerRequestDto) {
        String token = tokenService.generateToken(this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerCustomerRequestDto.email(), registerCustomerRequestDto.password())
        ));

        IntermediateRegisterResponseDto dto = IntermediateRegisterResponseDto.builder()
                .token(new TokenDto(token))
                .userId(user.getId())
                .build();

        return dto;
    }
}
