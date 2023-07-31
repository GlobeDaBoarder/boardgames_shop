package ua.rivnegray.boardgames_shop.DTO.request.create;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.UserProfile}
 */
public record CreateUserProfileDto(String email, String phone, String firstName,
                                   String lastName) implements Serializable {
}
