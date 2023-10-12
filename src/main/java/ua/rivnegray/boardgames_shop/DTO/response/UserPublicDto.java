package ua.rivnegray.boardgames_shop.DTO.response;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.User}
 */
public record UserPublicDto(Long id,
                            String email,
                            String phone,
                            String firstName,
                            String lastName,
                            String registrationStatus,
                            Set<UserRoleDto> roles,
                            Set<OrderDto> orders,
                            Set<AddressDto> addresses) implements Serializable {
}
