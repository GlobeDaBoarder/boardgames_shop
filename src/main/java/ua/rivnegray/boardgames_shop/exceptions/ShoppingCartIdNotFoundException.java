package ua.rivnegray.boardgames_shop.exceptions;

public class ShoppingCartIdNotFoundException extends RuntimeException {
    public ShoppingCartIdNotFoundException(long cartId) {
        super("Shopping cart with id " + cartId + " not found");
    }
}
