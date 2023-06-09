package ua.rivnegray.boardgames_shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Enable method level security based on annotations @PreAuthorize, @PostAuthorize, @Secured
public class SecurityConfig {

    private final UserDetailsService JpaDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService JpaDetailsService) {
        this.JpaDetailsService = JpaDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        // /shop/** permit all -- all shop logic
                        // /club/** permit all -- all club logic
                        .anyRequest().authenticated()
                )
                .userDetailsService(JpaDetailsService)
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
