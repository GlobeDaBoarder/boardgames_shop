package ua.rivnegray.boardgames_shop.DTO.request.update;

import jakarta.validation.constraints.Email;
import ua.rivnegray.boardgames_shop.utils.validation.ValidationConstants;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.User}
 */
public record UpdateEmailDto(@Email(regexp = ValidationConstants.emailRegex) String email) implements Serializable {
}
