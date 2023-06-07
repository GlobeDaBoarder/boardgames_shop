package ua.rivnegray.boardgames_shop.DTO.request.update;

import ua.rivnegray.boardgames_shop.model.OrderStatus;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.Order}
 */
public record UpdateOrderStatusDto(OrderStatus status) implements Serializable {
}
