package ua.rivnegray.boardgames_shop.DTO.request.update;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.ProductInShoppingCart}
 */
public record UpdateQuantityOfProductInShoppingCartDto(Integer quantity) implements Serializable {
}
