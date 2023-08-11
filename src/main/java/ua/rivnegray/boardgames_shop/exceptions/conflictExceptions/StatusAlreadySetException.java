package ua.rivnegray.boardgames_shop.exceptions.conflictExceptions;

import ua.rivnegray.boardgames_shop.model.OrderStatus;

public class StatusAlreadySetException extends ConflictException{
    public StatusAlreadySetException(OrderStatus parameterValue) {
        super("Order Status", parameterValue.name());
    }
}
