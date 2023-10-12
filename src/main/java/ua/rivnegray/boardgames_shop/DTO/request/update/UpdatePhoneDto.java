package ua.rivnegray.boardgames_shop.DTO.request.update;

import ua.rivnegray.boardgames_shop.utils.validation.annotation.PhoneNumber;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.User}
 */
public record UpdatePhoneDto(@PhoneNumber String phone) implements Serializable {
}
