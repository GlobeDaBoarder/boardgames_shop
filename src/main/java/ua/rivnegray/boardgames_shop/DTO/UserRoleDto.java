package ua.rivnegray.boardgames_shop.DTO;

import ua.rivnegray.boardgames_shop.model.user.UserPermission;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link ua.rivnegray.boardgames_shop.model.user.UserRole} entity
 */
public record UserRoleDto(Long id, String roleName, Set<UserPermission> permissions) implements Serializable {
}
