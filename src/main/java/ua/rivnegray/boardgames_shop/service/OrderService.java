package ua.rivnegray.boardgames_shop.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateOrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.model.OrderStatus;

import java.util.List;

public interface OrderService {
    @Transactional
    OrderDto createOrder(CreateOrderDto createOrderDto);

    @Transactional(readOnly = true)
    List<OrderDto> getAllOrders();

    @Transactional(readOnly = true)
    OrderDto getOrderById(Long orderId);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    OrderDto updateOrderStatus(Long orderId, OrderStatus orderStatus);

    @Transactional
    void cancelOrder(Long orderId);
}
