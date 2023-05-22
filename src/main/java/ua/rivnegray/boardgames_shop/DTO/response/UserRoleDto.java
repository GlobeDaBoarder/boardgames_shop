package ua.rivnegray.boardgames_shop.DTO.response;

import ua.rivnegray.boardgames_shop.model.UserPermission;
import ua.rivnegray.boardgames_shop.model.UserRole;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link UserRole} entity
 */
public record UserRoleDto(Long id, String roleName, Set<UserPermission> permissions) implements Serializable {
}
