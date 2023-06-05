package ua.rivnegray.boardgames_shop.DTO.request.create;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.UserCredentials and {@link ua.rivnegray.boardgames_shop.model.UserProfile}}
 */
public record CreateAnyUserDto(String username, String password, Set<Long> roleIds, String email,
                               String phone, String firstName,
                               String lastName) implements Serializable {
}
