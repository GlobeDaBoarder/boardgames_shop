package ua.rivnegray.boardgames_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateOrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.OrderIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.OrderMapper;
import ua.rivnegray.boardgames_shop.model.Order;
import ua.rivnegray.boardgames_shop.model.OrderStatus;
import ua.rivnegray.boardgames_shop.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;
    OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    private Order fetchOrderById(Long orderId) {
        return this.orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderIdNotFoundException(orderId));
    }
    @Override
    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        return this.orderMapper.orderToOrderDto(this.orderRepository.save(this.orderMapper.createOrderDtoToOrder(createOrderDto)));
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return this.orderRepository.findAll().stream()
                .map(order -> this.orderMapper.orderToOrderDto(order))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        return this.orderRepository.findById(orderId)
                .map(order -> this.orderMapper.orderToOrderDto(order))
                .orElseThrow(() -> new OrderIdNotFoundException(orderId));
    }

    @Override
    public OrderDto updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        Order order = fetchOrderById(orderId);
        order.setStatus(orderStatus);
        this.orderRepository.save(order);
        return this.orderMapper.orderToOrderDto(order);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = fetchOrderById(orderId);
        order.setStatus(OrderStatus.CANCELLED);
        this.orderRepository.save(order);
    }
}
