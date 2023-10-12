package ua.rivnegray.boardgames_shop.delegateService;

import generated.session.api.RegisterApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rivnegray.boardgames_shop.DTO.request.RegisterCustomerRequestDto;
import ua.rivnegray.boardgames_shop.DTO.response.IntermediateRegisterResponseDto;
import ua.rivnegray.boardgames_shop.DTO.response.TokenDto;
import ua.rivnegray.boardgames_shop.service.SessionsService;

import java.net.URI;

@Service
public class RegisterApiDelegateImpl implements RegisterApiDelegate {
    private final SessionsService sessionsService;

    @Autowired
    RegisterApiDelegateImpl(SessionsService sessionsService) {
        this.sessionsService = sessionsService;
    }


    @Override
    public ResponseEntity<TokenDto> registerUser(RegisterCustomerRequestDto registerCustomerRequestDto) {
        IntermediateRegisterResponseDto intermediateRegisterResponseDto = this.sessionsService.register(registerCustomerRequestDto);

        URI location = ServletUriComponentsBuilder.fromUriString("/users")
                .path("/{id}")
                .buildAndExpand(intermediateRegisterResponseDto.userId())
                .toUri();

        return ResponseEntity.created(location).body(intermediateRegisterResponseDto.token());
    }
}
