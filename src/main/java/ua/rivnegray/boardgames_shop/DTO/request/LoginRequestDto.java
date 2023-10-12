package ua.rivnegray.boardgames_shop.DTO.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import ua.rivnegray.boardgames_shop.utils.validation.ValidationConstants;

public record LoginRequestDto(
        @NotBlank(message = "email is required")
        @Email(regexp = ValidationConstants.emailRegex)
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 5, message = "Password must be at least 8 characters long")
        String password
) {
}
