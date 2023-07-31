package ua.rivnegray.boardgames_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInOrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.model.ProductInOrder;
import ua.rivnegray.boardgames_shop.model.ProductInShoppingCart;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;
import ua.rivnegray.boardgames_shop.repository.ShoppingCartRepository;

@Mapper(componentModel = "spring", uses = {BoardGameRepository.class, ShoppingCartRepository.class})
public interface ProductMapper {

    @Mapping(target = "productId", source = "product.id")
    ProductInShoppingCartDto toProductInShoppingCartDto(ProductInShoppingCart productInShoppingCart);

    @Mapping(target = "order", ignore = true)
    ProductInOrder toProductInOrder(ProductInShoppingCart productInShoppingCart);

    @Mapping(target = "productId", source = "product.id")
    ProductInOrderDto toProductInOrderDto(ProductInOrder productInOrder);
}
