package ua.rivnegray.boardgames_shop.mapper;

import org.mapstruct.Mapper;
import ua.rivnegray.boardgames_shop.DTO.request.create.AddProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.model.ProductInShoppingCart;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductInShoppingCart toProductInShoppingCart(Long cartId, AddProductInShoppingCartDto addProductInShoppingCartDto);

    ProductInShoppingCartDto toProductInShoppingCartDto(ProductInShoppingCart productInShoppingCart);
}
