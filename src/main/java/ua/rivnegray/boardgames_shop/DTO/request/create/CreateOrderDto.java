package ua.rivnegray.boardgames_shop.DTO.request.create;

import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;

import java.io.Serializable;
import java.util.List;

public record CreateOrderDto(List<ProductInShoppingCartDto> productsInShoppingCart,
                             AddAndUpdateAddressDto addAndUpdateAddressDto,
                             UserInfoForOrderDto userProfileDto) implements Serializable {
}
