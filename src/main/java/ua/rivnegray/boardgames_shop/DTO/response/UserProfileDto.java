package ua.rivnegray.boardgames_shop.DTO.response;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.UserProfile}
 */
public record UserProfileDto(Long id, String email, String phone, String firstName,
                             String lastName) implements Serializable {
}
