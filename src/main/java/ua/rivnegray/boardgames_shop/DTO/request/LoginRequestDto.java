package ua.rivnegray.boardgames_shop.DTO.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record LoginRequestDto(
        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password
) {
}
