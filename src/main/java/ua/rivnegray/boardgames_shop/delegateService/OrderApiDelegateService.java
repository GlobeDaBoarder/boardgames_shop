package ua.rivnegray.boardgames_shop.delegateService;

import generated.order.api.OrdersApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateOrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.model.OrderStatus;
import ua.rivnegray.boardgames_shop.service.OrderService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class OrderApiDelegateService implements OrdersApiDelegate {

    OrderService orderService;

    @Autowired
    public OrderApiDelegateService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return OrdersApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> cancelOrder(Long orderId) {
        this.orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<OrderDto> createOrder(CreateOrderDto createOrderDto) {
        OrderDto orderDto = this.orderService.createOrder(createOrderDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderDto.id())
                .toUri();

        return ResponseEntity.created(location).body(orderDto);
    }

    @Override
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(this.orderService.getAllOrders());
    }

    @Override
    public ResponseEntity<OrderDto> getOrderById(Long orderId) {
        return ResponseEntity.ok(this.orderService.getOrderById(orderId));
    }

    @Override
    public ResponseEntity<OrderDto> updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        return ResponseEntity.ok(this.orderService.updateOrderStatus(orderId, orderStatus));
    }
}
