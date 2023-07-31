package ua.rivnegray.boardgames_shop.DTO.request;

import ua.rivnegray.boardgames_shop.DTO.request.create.CreateCustomerUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.MapShoppingCartDto;

public record RegisterRequestWithMapShoppingCartDto(
        CreateCustomerUserDto createCustomerUserDto,
        MapShoppingCartDto mapShoppingCartDto) {
}
