package ua.rivnegray.boardgames_shop.DTO.request.update;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.UserCredentials}
 */
public record UpdatePasswordDto(String password) implements Serializable {
}
