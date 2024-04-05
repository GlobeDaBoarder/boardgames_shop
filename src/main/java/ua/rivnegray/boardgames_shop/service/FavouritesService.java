package ua.rivnegray.boardgames_shop.service;

import jakarta.transaction.Transactional;
import ua.rivnegray.boardgames_shop.DTO.request.create.MapProductInFavouritesDto;

import java.util.List;

public interface FavouritesService {

    @Transactional
    void mapFavourites(List<MapProductInFavouritesDto> productInFavouritesDtos);
}
