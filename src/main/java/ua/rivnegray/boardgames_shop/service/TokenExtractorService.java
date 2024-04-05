package ua.rivnegray.boardgames_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.UserIdNotFoundException;
import ua.rivnegray.boardgames_shop.model.User;
import ua.rivnegray.boardgames_shop.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class TokenExtractorService {

    private final UserRepository userRepository;

    public Long extractCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
        return jwtPrincipal.getClaim("id");
    }

    public User extractCurrentUser() {
        Long userId = extractCurrentUserId();
        return userRepository.findById(userId).orElseThrow(() ->  new UserIdNotFoundException(userId));
    }
}
