package ua.rivnegray.boardgames_shop.DTO.request;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.User}
 */
public record CreateAndUpdateUserDto(String username, String password, String email, String phone, String imageUrl,
                                     Set<Long> roleIds) implements Serializable {
}
