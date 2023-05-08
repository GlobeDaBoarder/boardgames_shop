package ua.rivnegray.boardgames_shop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.api.UsersApi;
import io.swagger.api.UsersApiController;

@Configuration
public class ControllerConfig {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HttpServletRequest request;

    @Bean
    public UsersApi myController() {
        return new UsersApiController(objectMapper, request);

    }
}
