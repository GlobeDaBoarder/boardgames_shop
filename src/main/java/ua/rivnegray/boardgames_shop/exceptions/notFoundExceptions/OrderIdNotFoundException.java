package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

public class OrderIdNotFoundException extends ResourceIdNotFoundException {
    public OrderIdNotFoundException(Long orderId) {
        super("Order id not found");
    }
}
