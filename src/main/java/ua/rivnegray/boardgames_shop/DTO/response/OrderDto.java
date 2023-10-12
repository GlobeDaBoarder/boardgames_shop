package ua.rivnegray.boardgames_shop.DTO.response;

import ua.rivnegray.boardgames_shop.model.OrderStatus;
import ua.rivnegray.boardgames_shop.model.PaymentStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.Order}
 */
public record OrderDto(Long id, Long userId, Set<ProductInOrderDto> orderItems, OrderStatus currentStatus,
                        BigDecimal totalPrice, AddressDto address, PaymentStatus paymentStatus,
                        Set<OrderStatusDateDto> orderStatusHistory) implements Serializable {
    /**
     * DTO for {@link ua.rivnegray.boardgames_shop.model.OrderStatusDate}
     */
    public record OrderStatusDateDto(OrderStatus status, LocalDateTime date) implements Serializable {
    }
}
