package ua.rivnegray.boardgames_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.rivnegray.boardgames_shop.DTO.response.FavouriteProductCreationResponseDto;
import ua.rivnegray.boardgames_shop.DTO.response.FavouriteProductDto;
import ua.rivnegray.boardgames_shop.model.FavouriteProduct;

@Mapper(componentModel = "spring", uses = {BoardGameMapperService.class})
public interface FavouriteProductMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "productId", source = "boardGame.id")
    FavouriteProductCreationResponseDto toFavouriteProductCreationResponseDto(FavouriteProduct favouriteProduct);

    @Mapping(target = "boardGame", source = "boardGame")
    FavouriteProductDto toFavouriteProductDto(FavouriteProduct favouriteProduct);
}
