package ua.rivnegray.boardgames_shop.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateOrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.OrderIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.UserIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.OrderMapper;
import ua.rivnegray.boardgames_shop.mapper.UserMapper;
import ua.rivnegray.boardgames_shop.model.Address;
import ua.rivnegray.boardgames_shop.model.Order;
import ua.rivnegray.boardgames_shop.model.OrderStatus;
import ua.rivnegray.boardgames_shop.model.PaymentStatus;
import ua.rivnegray.boardgames_shop.model.ProductInOrder;
import ua.rivnegray.boardgames_shop.model.UserProfile;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;
import ua.rivnegray.boardgames_shop.repository.OrderRepository;
import ua.rivnegray.boardgames_shop.repository.UserProfileRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;
    OrderMapper orderMapper;

    UserMapper userMapper;
    UserProfileRepository userProfileRepository;

    BoardGameRepository boardGameRepository;

    EntityManager entityManager;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, UserMapper userMapper,
                            UserProfileRepository userProfileRepository, BoardGameRepository boardGameRepository,
                            EntityManager entityManager) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userMapper = userMapper;
        this.userProfileRepository = userProfileRepository;
        this.boardGameRepository = boardGameRepository;
        this.entityManager = entityManager;
    }

    // admin orders operations
    @Override
    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        // todo check for existing user profile by email or phone

        UserProfile userProfile = this.userMapper.toUserProfile(createOrderDto.userProfileDto());

        Address address = this.userMapper.toAddress(createOrderDto.addAndUpdateAddressDto());
        userProfile.getAddresses().add(address);

        this.userProfileRepository.save(userProfile);

        Set<ProductInOrder> productInOrders = createOrderDto.mapShoppingCartDto().simpleProductInShoppingCartDtos().stream()
                .map(simpleProductInShoppingCartDto -> ProductInOrder.builder()
                        .product(this.boardGameRepository.findById(simpleProductInShoppingCartDto.productId())
                                .orElseThrow(() -> new BoardGameIdNotFoundException(simpleProductInShoppingCartDto.productId())))
                        .quantity(simpleProductInShoppingCartDto.quantity())
                        .build()
                )
                .collect(Collectors.toSet());

        Order order = Order.builder()
                .userProfile(userProfile)
                .orderItems(productInOrders)
                .totalPrice(
                        productInOrders.stream()
                                .map(ProductInOrder::calculateTotalPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
                .status(OrderStatus.PLACED)
                .address(address)
                .dateOrderPlaced(LocalDateTime.now())
                .paymentStatus(PaymentStatus.UNPAID)
                .build();

        order.getOrderItems().forEach(productInOrder -> productInOrder.setOrder(order));

        this.orderRepository.save(order);

        entityManager.flush();
        entityManager.refresh(order);

        return this.orderMapper.orderToOrderDto(order);
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
        return this.getCurrentUserOrders().stream()
                .map(order -> this.orderMapper.orderToOrderDto(order))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getMyOrderById(Long orderId) {
        return this.orderRepository.findByIdAndUserProfile_Id(orderId, getCurrentUserId())
                .map(order -> this.orderMapper.orderToOrderDto(order))
                .orElseThrow(() -> new OrderIdNotFoundException(orderId));
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
}
