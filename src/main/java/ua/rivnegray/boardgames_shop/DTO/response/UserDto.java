package ua.rivnegray.boardgames_shop.DTO.response;

import ua.rivnegray.boardgames_shop.model.User;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link User} entity
 */

/// todo add annotations for json
public record UserDto(Long id, String username, String password, String email, String phone, String imageUrl,
                      Set<UserRoleDto> roles) implements Serializable {
}
