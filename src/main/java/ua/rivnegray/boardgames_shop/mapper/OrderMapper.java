package ua.rivnegray.boardgames_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateOrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.model.Order;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, UserMapper.class})
public interface OrderMapper {
    Order createOrderDtoToOrder(CreateOrderDto createOrderDto);

    @Mapping(target = "userProfileId", source = "userProfile.id")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "orderItems", source = "orderItems")
    OrderDto orderToOrderDto(Order order);
}
