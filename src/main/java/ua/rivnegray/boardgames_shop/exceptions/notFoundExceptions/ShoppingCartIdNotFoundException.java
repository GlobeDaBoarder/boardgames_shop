package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import ua.rivnegray.boardgames_shop.model.ShoppingCart;

public class ShoppingCartIdNotFoundException extends ResourceIdNotFoundException {
    public ShoppingCartIdNotFoundException(long cartId) {
        super(ShoppingCart.class.getName(), cartId);
    }
}
