package ua.rivnegray.boardgames_shop.delegateService;

import generated.session.api.LoginApiDelegate;
import generated.session.api.RegisterApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rivnegray.boardgames_shop.DTO.request.LoginRequestDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateCustomerUserDto;
import ua.rivnegray.boardgames_shop.DTO.response.LoginResponseDto;
import ua.rivnegray.boardgames_shop.service.SessionsService;

import java.net.URI;
import java.util.Optional;

@Service
@Primary
public class SessionDelegateService implements LoginApiDelegate, RegisterApiDelegate {

    // TODO mapping of shpping cart from localstorage/cookies on login or register

    SessionsService sessionsService;

    @Autowired
    public SessionDelegateService(SessionsService sessionsService) {
        this.sessionsService = sessionsService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return LoginApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<LoginResponseDto> registerUser(CreateCustomerUserDto createCustomerUserDto) {
        LoginResponseDto loginResponse = this.sessionsService.register(createCustomerUserDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(loginResponse.user().id())
                .toUri();

        return ResponseEntity.created(location).body(loginResponse);
    }

    @Override
    public ResponseEntity<LoginResponseDto> loginUser(LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(this.sessionsService.login(loginRequestDto));
    }
}
