package ua.rivnegray.boardgames_shop.DTO.request.update;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.User}
 */
public record UpdatePasswordDto(
        String oldPassword,
        String newPassword) implements Serializable {
}
