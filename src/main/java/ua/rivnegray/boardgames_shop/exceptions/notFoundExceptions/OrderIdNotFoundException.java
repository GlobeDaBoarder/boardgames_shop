package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import ua.rivnegray.boardgames_shop.model.Order;

public class OrderIdNotFoundException extends ResourceIdNotFoundException {
    public OrderIdNotFoundException(Long id) {
        super(Order.class.getName(), id);
    }
}
