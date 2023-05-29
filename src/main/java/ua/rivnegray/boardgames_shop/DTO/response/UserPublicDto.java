package ua.rivnegray.boardgames_shop.DTO.response;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.UserCredentials}
 */
public record UserPublicDto(String username, Set<UserRoleDto> roles, String email, String phone,
                            String firstName, String lastName,
                            Set<AddressDto> addresses) implements Serializable {
}
