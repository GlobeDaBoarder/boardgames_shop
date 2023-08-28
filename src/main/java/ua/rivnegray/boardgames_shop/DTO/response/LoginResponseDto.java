package ua.rivnegray.boardgames_shop.DTO.response;

import java.io.Serializable;

public record LoginResponseDto(UserPublicDto user, String token) implements Serializable {
}
