package ua.rivnegray.boardgames_shop.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private static final Logger LOGGR = LoggerFactory.getLogger(SecurityConfig.class);

    private final UserDetailsService JpaDetailsService;
    private  RSAKey rsaKey;

    AuthenticationEntryPointImpl authenticationEntryPoint;

    @Autowired
    public SecurityConfig(UserDetailsService JpaDetailsService, AuthenticationEntryPointImpl authenticationEntryPoint) {
        this.JpaDetailsService = JpaDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(this.passwordEncoder());
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/token").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/boardgames/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/orders").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
                        .authenticationEntryPoint(this.authenticationEntryPoint))
                .userDetailsService(JpaDetailsService)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        this.rsaKey = Jwks.generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwks) {
        final NimbusJwtEncoder nimbusJwtEncoder = new NimbusJwtEncoder(jwks);

        return parameters -> {
            LOGGR.debug("Encoding JWT with parameters: " + parameters.getClaims());
            Jwt jwt = nimbusJwtEncoder.encode(parameters);
            LOGGR.debug("Encoded JWT value: " + jwt.getTokenValue());
            LOGGR.debug("Encoded JWT claims: " + jwt.getClaims());
            return jwt;
        };
    }

    @Bean
    public JwtDecoder jwtDecoder() throws JOSEException {
        final NimbusJwtDecoder nimbusJwtDecoder = NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();

        return token -> {
            LOGGR.debug("Decoding JWT: " + token);
            Jwt jwt = nimbusJwtDecoder.decode(token);
            LOGGR.debug("Decoded JWT claims: " + jwt.getClaims());
            LOGGR.debug("Encoded JWT subject: " + jwt.getSubject());
            return jwt;
        };
    }
}
