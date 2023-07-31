package ua.rivnegray.boardgames_shop.DTO.request;

import ua.rivnegray.boardgames_shop.DTO.request.create.MapShoppingCartDto;

public record LoginRequestWithMapShoppingCartDto(
        LoginRequestDto loginRequestDto,
        MapShoppingCartDto mapShoppingCartDto) {
}
