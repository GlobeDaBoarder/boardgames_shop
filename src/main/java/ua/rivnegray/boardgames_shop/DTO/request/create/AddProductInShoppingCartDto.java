package ua.rivnegray.boardgames_shop.DTO.request.create;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.ProductInShoppingCart}
 */
public record AddProductInShoppingCartDto(Long productId, Integer quantity) implements Serializable {
}
