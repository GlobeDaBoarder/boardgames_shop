package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import ua.rivnegray.boardgames_shop.model.FavouriteProduct;

public class FavouriteProductNotFoundException extends ResourceIdNotFoundException {
    public FavouriteProductNotFoundException(Long favoriteId) {
        super(FavouriteProduct.class.getSimpleName(), favoriteId);
    }
}
