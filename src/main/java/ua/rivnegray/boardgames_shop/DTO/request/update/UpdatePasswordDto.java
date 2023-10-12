package ua.rivnegray.boardgames_shop.DTO.request.update;

import ua.rivnegray.boardgames_shop.utils.validation.annotation.Password;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.User}
 */
public record UpdatePasswordDto(
        String oldPassword,
        @Password
        String newPassword) implements Serializable {
}
