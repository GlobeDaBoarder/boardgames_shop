package ua.rivnegray.boardgames_shop.DTO.request.create;

import jakarta.validation.constraints.Email;
import ua.rivnegray.boardgames_shop.utils.validation.ValidationConstants;
import ua.rivnegray.boardgames_shop.utils.validation.annotation.Password;
import ua.rivnegray.boardgames_shop.utils.validation.annotation.PhoneNumber;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.UserCredentials and {@link ua.rivnegray.boardgames_shop.model.UserProfile}}
 */
public record CreateAnyUserDto(String username,
                               @Password
                               String password,
                               Set<Long> roleIds,
                               @Email(regexp = ValidationConstants.emailRegex)
                               String email,
                               @PhoneNumber
                               String phone,
                               String firstName,
                               String lastName) implements Serializable {
}
