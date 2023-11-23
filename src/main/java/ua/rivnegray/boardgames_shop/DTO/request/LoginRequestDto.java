package ua.rivnegray.boardgames_shop.DTO.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import ua.rivnegray.boardgames_shop.utils.validation.ValidationConstants;

public record LoginRequestDto(
        @NotBlank(message = "email is required")
        @Email(regexp = ValidationConstants.EMAIL)
        @Schema(example = "admin@rivnegray.ua")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 5, message = "Password must be at least 8 characters long")
        @Schema(example = "admin")
        String password
) {
}
