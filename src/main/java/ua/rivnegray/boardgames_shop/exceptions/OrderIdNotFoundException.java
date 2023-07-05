package ua.rivnegray.boardgames_shop.exceptions;

public class OrderIdNotFoundException extends RuntimeException {
    public OrderIdNotFoundException(Long orderId) {
        super("Order with id " + orderId + " not found");
    }
}
