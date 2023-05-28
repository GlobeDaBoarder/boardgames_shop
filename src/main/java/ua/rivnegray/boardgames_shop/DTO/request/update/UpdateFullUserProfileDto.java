package ua.rivnegray.boardgames_shop.DTO.request.update;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.UserProfile}
 */
public record UpdateFullUserProfileDto(String email, String phone, String firstName,
                                       String lastName) implements Serializable {
}
