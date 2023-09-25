package ua.rivnegray.boardgames_shop.delegateService;

import generated.session.api.LoginApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.LoginRequestDto;
import ua.rivnegray.boardgames_shop.DTO.response.TokenDto;
import ua.rivnegray.boardgames_shop.service.SessionsService;

@Service
public class LoginApiDelegateImpl implements LoginApiDelegate {
    private final SessionsService sessionsService;

    @Autowired
    LoginApiDelegateImpl(SessionsService sessionsService) {
        this.sessionsService = sessionsService;
    }

    @Override
    public ResponseEntity<TokenDto> loginUser(LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(this.sessionsService.login(loginRequestDto));
    }
}
