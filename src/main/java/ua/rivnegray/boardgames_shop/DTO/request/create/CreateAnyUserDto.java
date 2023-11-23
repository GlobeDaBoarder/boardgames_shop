package ua.rivnegray.boardgames_shop.DTO.request.create;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import ua.rivnegray.boardgames_shop.utils.validation.ValidationConstants;
import ua.rivnegray.boardgames_shop.utils.validation.annotation.Password;
import ua.rivnegray.boardgames_shop.utils.validation.annotation.PhoneNumber;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.User }
 */
public record CreateAnyUserDto(
        @Email(regexp = ValidationConstants.EMAIL)
        @NotNull
        String email,
        @Password
        @NotNull
        String password,
        @PhoneNumber
        @NotNull
        String phone,
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @Nullable
        String nickName,
        @NotNull
        Set<Long> roleIds) implements Serializable {
}
