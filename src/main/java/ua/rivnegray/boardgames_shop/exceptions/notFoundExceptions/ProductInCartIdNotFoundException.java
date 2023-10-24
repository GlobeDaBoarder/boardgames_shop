package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import ua.rivnegray.boardgames_shop.model.ProductInShoppingCart;

public class ProductInCartIdNotFoundException extends ResourceIdNotFoundException {
    public ProductInCartIdNotFoundException(Long id) {
        super(ProductInShoppingCart.class.toString(), id);
    }
}
