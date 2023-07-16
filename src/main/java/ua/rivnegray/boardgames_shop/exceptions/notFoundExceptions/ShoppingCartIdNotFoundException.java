package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

public class ShoppingCartIdNotFoundException extends ResourceIdNotFoundException {
    public ShoppingCartIdNotFoundException(long cartId) {
        super("Shopping cart with id " + cartId + " not found");
    }
}
