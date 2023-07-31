package ua.rivnegray.boardgames_shop.DTO.request.create;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.ShoppingCart}
 */
public record MapShoppingCartDto(
        Set<SimpleProductInShoppingCartDto> simpleProductInShoppingCartDtos) implements Serializable {
    /**
     * DTO for {@link ua.rivnegray.boardgames_shop.model.ProductInShoppingCart}
     */
    public record SimpleProductInShoppingCartDto(Long productId, Integer quantity) implements Serializable {
    }
}
