package ua.rivnegray.boardgames_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInOrderDto;
import ua.rivnegray.boardgames_shop.model.Order;
import ua.rivnegray.boardgames_shop.model.ProductInOrder;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, UserMapper.class})
public interface OrderMapper {

    // todo try to get rid of this method
    @Named("orderItemsToOrderItems")
    default Set<ProductInOrderDto> orderItemsToOrderItems(Set<ProductInOrder> orderItems) {
        Set<ProductInOrderDto> productInOrderDtos = new HashSet<>();
        for (ProductInOrder productInOrder : orderItems) {
            productInOrderDtos.add(new ProductInOrderDto(productInOrder.getId(),
                    productInOrder.getProduct().getId(), productInOrder.getQuantity()));
        }

        return productInOrderDtos;
    }

    @Mapping(target = "userProfileId", source = "userProfile.id")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "orderItems", source = "orderItems", qualifiedByName = "orderItemsToOrderItems")
    OrderDto orderToOrderDto(Order order);
}
