package ua.rivnegray.boardgames_shop.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateOrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.model.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    // admin orders operations
    @Transactional
    OrderDto createOrder(CreateOrderDto createOrderDto);

    @Transactional(readOnly = true)
    List<OrderDto> getAllOrders();

    @Transactional(readOnly = true)
    OrderDto getOrderById(Long orderId);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    OrderDto updateOrderStatus(Long orderId, OrderStatus orderStatus);

    // my order operations
    @Transactional
    void cancelMyOrder(Long orderId);

    @Transactional(readOnly = true)
    List<OrderDto> getMyOrders();

    @Transactional(readOnly = true)
    OrderDto getMyOrderById(Long orderId);

    @Transactional
    byte[] exportOrdersToExcel(LocalDate startDate, LocalDate endDate);
}
