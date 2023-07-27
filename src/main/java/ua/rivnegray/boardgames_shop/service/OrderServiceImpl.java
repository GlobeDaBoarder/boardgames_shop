package ua.rivnegray.boardgames_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateOrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.OrderIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.UserIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.OrderMapper;
import ua.rivnegray.boardgames_shop.model.Order;
import ua.rivnegray.boardgames_shop.model.OrderStatus;
import ua.rivnegray.boardgames_shop.repository.OrderRepository;
import ua.rivnegray.boardgames_shop.repository.UserProfileRepository;

import java.util.List;
import java.util.Set;
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

    private Long getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
        return jwtPrincipal.getClaim("id");
    }

    private Set<Order> getCurrentUserOrders(){
        return this.orderRepository.findAllByUserProfile_Id(getCurrentUserId());
    }

    // admin orders operations
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

    // my order operations

    @Override
    public void cancelMyOrder(Long orderId) {
        Order orderToCancel = this.orderRepository.findByIdAndUserProfile_Id(orderId, getCurrentUserId())
                        .orElseThrow(() -> new OrderIdNotFoundException(orderId));
        orderToCancel.setStatus(OrderStatus.CANCELLED);
        this.orderRepository.save(orderToCancel);
    }

    @Override
    public List<OrderDto> getMyOrders() {
        return this.orderRepository.findAllByUserProfile_Id(getCurrentUserId()).stream()
                .map(order -> this.orderMapper.orderToOrderDto(order))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getMyOrderById(Long orderId) {
        return this.orderRepository.findByIdAndUserProfile_Id(orderId, getCurrentUserId())
                .map(order -> this.orderMapper.orderToOrderDto(order))
                .orElseThrow(() -> new OrderIdNotFoundException(orderId));
    }
}
