package ua.rivnegray.boardgames_shop.DTO.request.create;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.UserCredentials} and {@link ua.rivnegray.boardgames_shop.model.UserProfile}
 */
public record CreateCustomerUserDto(String username, String password, String userProfileEmail, String userProfilePhone,
                                    String userProfileFirstName, String userProfileLastName) implements Serializable {
}
