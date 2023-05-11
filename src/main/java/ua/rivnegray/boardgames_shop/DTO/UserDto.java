package ua.rivnegray.boardgames_shop.DTO;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link ua.rivnegray.boardgames_shop.model.user.User} entity
 */
public record UserDto(Long id, String username, String password, String email, String phone, String imageUrl,
                      Set<UserRoleDto> roles) implements Serializable {
}
