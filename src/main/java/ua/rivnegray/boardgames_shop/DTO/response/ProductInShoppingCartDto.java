package ua.rivnegray.boardgames_shop.DTO.response;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.ProductInShoppingCart}
 */
public record ProductInShoppingCartDto(Long id, Long productId, Integer quantity) implements Serializable {
}
