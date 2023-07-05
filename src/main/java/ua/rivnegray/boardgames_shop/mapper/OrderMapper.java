package ua.rivnegray.boardgames_shop.mapper;

import org.mapstruct.Mapper;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateOrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order createOrderDtoToOrder(CreateOrderDto createOrderDto);

    OrderDto orderToOrderDto(Order order);
}
