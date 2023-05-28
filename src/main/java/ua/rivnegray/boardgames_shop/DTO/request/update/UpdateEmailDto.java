package ua.rivnegray.boardgames_shop.DTO.request.update;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.UserProfile}
 */
public record UpdateEmailDto(String email) implements Serializable {
}
