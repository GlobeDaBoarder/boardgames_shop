package ua.rivnegray.boardgames_shop.DTO.response;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.ProductInShoppingCart}
 */
public record ProductInShoppingCartDto(Long productInCartId, Long productId, Integer quantity) implements Serializable {
}
