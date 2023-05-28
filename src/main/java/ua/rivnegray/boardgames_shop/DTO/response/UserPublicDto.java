package ua.rivnegray.boardgames_shop.DTO.response;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.UserCredentials}
 */
public record UserPublicDto(String username, Set<UserRoleDto> roles, String userProfileEmail, String userProfilePhone,
                            String userProfileFirstName, String userProfileLastName,
                            Set<AddressDto> userProfileAddresses) implements Serializable {
}
