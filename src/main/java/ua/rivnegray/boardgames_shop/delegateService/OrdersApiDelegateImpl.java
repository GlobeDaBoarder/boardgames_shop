package ua.rivnegray.boardgames_shop.delegateService;

import generated.order.api.OrdersApiDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateOrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.model.OrderStatus;
import ua.rivnegray.boardgames_shop.service.OrderService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrdersApiDelegateImpl implements OrdersApiDelegate {

    private final OrderService orderService;

    @Autowired
    OrdersApiDelegateImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    // admin orders operations

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

    // my orders operations


    @Override
    public ResponseEntity<Void> cancelMyOrder(Long orderId) {
        this.orderService.cancelMyOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<OrderDto> getMyOrderById(Long orderId) {
        return ResponseEntity.ok(this.orderService.getMyOrderById(orderId));
    }

    @Override
    public ResponseEntity<List<OrderDto>> getMyOrders() {
        return ResponseEntity.ok(this.orderService.getMyOrders());
    }

    @Override
    public ResponseEntity<Resource> exportOrdersToExcel(LocalDate startDate, LocalDate endDate) {
        byte[] excelData = this.orderService.exportOrdersToExcel(startDate, endDate);
        ByteArrayResource resource = new ByteArrayResource(excelData);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=orders.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(excelData.length)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }
}
