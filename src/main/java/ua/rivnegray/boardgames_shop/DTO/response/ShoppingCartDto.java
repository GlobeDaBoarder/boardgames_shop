package ua.rivnegray.boardgames_shop.DTO.response;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.ShoppingCart}
 */
public record ShoppingCartDto(Long id, Long userProfileId,
                              Set<ProductInShoppingCartDto> products) implements Serializable {
}
