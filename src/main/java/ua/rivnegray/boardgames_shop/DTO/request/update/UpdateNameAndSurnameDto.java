package ua.rivnegray.boardgames_shop.DTO.request.update;

import ua.rivnegray.boardgames_shop.utils.validation.annotation.NameAndSurname;

public record UpdateNameAndSurnameDto(
        @NameAndSurname
        String nameAndSurname) {
}
