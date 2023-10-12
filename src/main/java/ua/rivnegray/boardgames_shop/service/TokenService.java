package ua.rivnegray.boardgames_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.model.SecurityUser;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtEncoder encoder;

    public String generateToken(Authentication authentication) {
        SecurityUser securityUser= (SecurityUser) authentication.getPrincipal();

        Instant now = Instant.now();
        Set<String> scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                // TODO: change to 1 hour for production
                .expiresAt(now.plus(14, ChronoUnit.DAYS))
                .subject(authentication.getName())
                .claim("id", securityUser.getId())
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
