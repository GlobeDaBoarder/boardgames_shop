package ua.rivnegray.boardgames_shop.DTO.request.create;

import jakarta.validation.constraints.Email;
import ua.rivnegray.common_utils.ValidationConstants;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.UserCredentials and {@link ua.rivnegray.boardgames_shop.model.UserProfile}}
 */
public record CreateAnyUserDto(String username,
                               String password,
                               Set<Long> roleIds,
                               @Email(regexp = ValidationConstants.emailRegex)String email,
                               String phone,
                               String firstName,
                               String lastName) implements Serializable {
}
