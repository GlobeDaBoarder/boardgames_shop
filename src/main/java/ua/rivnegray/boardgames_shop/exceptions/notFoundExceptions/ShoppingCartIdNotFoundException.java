package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

public class ShoppingCartIdNotFoundException extends ResourceIdNotFoundException {
    public ShoppingCartIdNotFoundException(long cartId) {
        super("Shopping cart id not found");
    }
}
