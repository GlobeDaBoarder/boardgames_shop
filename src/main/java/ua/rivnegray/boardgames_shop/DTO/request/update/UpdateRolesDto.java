package ua.rivnegray.boardgames_shop.DTO.request.update;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.UserCredentials}
 */
public record UpdateRolesDto(Set<Long> roleIds) implements Serializable {
}
