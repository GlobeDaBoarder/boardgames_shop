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
public record OrderDto(Long id, LocalDateTime dateCreated, LocalDateTime dateUpdated, Long userProfileId,
                       Set<ProductInOrderDto> orderItems, OrderStatus status, LocalDateTime orderDate,
                       BigDecimal totalPrice, AddressDto address, LocalDateTime dateOrderPlaced,
                       LocalDateTime dateOrderDelivered, PaymentStatus paymentStatus) implements Serializable {
}
