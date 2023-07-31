package ua.rivnegray.boardgames_shop.delegateService;

import generated.session.api.RegisterApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rivnegray.boardgames_shop.DTO.request.RegisterRequestWithMapShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.LoginResponseDto;
import ua.rivnegray.boardgames_shop.service.SessionsService;

import java.net.URI;

@Service
public class RegisterApiDelegateImpl implements RegisterApiDelegate {
    SessionsService sessionsService;

    @Autowired
    public RegisterApiDelegateImpl(SessionsService sessionsService) {
        this.sessionsService = sessionsService;
    }

    @Override
    public ResponseEntity<LoginResponseDto> registerUser(RegisterRequestWithMapShoppingCartDto registerRequestDto) {
        LoginResponseDto loginResponse = this.sessionsService.register(registerRequestDto.createCustomerUserDto(),
                registerRequestDto.mapShoppingCartDto());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(loginResponse.user().id())
                .toUri();

        return ResponseEntity.created(location).body(loginResponse);
    }
}
