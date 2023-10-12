package ua.rivnegray.boardgames_shop.DTO.request.create;

import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;

import java.io.Serializable;

public record CreateOrderDto(MapShoppingCartDto mapShoppingCartDto,
                             AddAndUpdateAddressDto addAndUpdateAddressDto,
                             UserInfoForOrderDto userProfileDto) implements Serializable {
}
