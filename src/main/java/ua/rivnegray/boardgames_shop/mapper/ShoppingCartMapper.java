package ua.rivnegray.boardgames_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ua.rivnegray.boardgames_shop.DTO.request.create.AddProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.ShoppingCartDto;
import ua.rivnegray.boardgames_shop.model.ProductInShoppingCart;
import ua.rivnegray.boardgames_shop.model.ShoppingCart;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface ShoppingCartMapper {

    @Mapping(target = "userProfileId", source = "userProfile.id")
    @Mapping(target = "productsInShoppingCartDto", source = "productsInShoppingCart")
    ShoppingCartDto toShoppingCartDto(ShoppingCart shoppingCart);



}
