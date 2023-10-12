package ua.rivnegray.boardgames_shop.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import ua.rivnegray.boardgames_shop.utils.validation.ValidationConstants;
import ua.rivnegray.boardgames_shop.utils.validation.annotation.FirstNameAndLastName;
import ua.rivnegray.boardgames_shop.utils.validation.annotation.Password;
import ua.rivnegray.boardgames_shop.utils.validation.annotation.PhoneNumber;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.UserCredentials} and {@link ua.rivnegray.boardgames_shop.model.UserProfile}
 */
public record RegisterCustomerRequestDto(
        @Email(regexp = ValidationConstants.emailRegex)
        @NotBlank
        String email,
        @NotBlank
        @Password
        String password,

        @PhoneNumber
        @NotBlank
        String phone,

        @FirstNameAndLastName
        @NotBlank
        String firstNameAndLastName) implements Serializable{
}
