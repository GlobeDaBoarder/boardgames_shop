package ua.rivnegray.boardgames_shop.delegateService;

import generated.session.api.LoginApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.LoginRequestWithMapShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.LoginResponseDto;
import ua.rivnegray.boardgames_shop.service.SessionsService;

@Service
public class LoginApiDelegateImpl implements LoginApiDelegate {
    private final SessionsService sessionsService;

    @Autowired
    LoginApiDelegateImpl(SessionsService sessionsService) {
        this.sessionsService = sessionsService;
    }

    @Override
    public ResponseEntity<LoginResponseDto> loginUser(LoginRequestWithMapShoppingCartDto loginRequestDto) {
        return ResponseEntity.ok(this.sessionsService.login(loginRequestDto.loginRequestDto(),
                loginRequestDto.mapShoppingCartDto()));
    }
}
