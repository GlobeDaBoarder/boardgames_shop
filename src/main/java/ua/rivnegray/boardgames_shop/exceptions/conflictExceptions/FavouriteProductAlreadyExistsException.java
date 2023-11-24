package ua.rivnegray.boardgames_shop.exceptions.conflictExceptions;

public class FavouriteProductAlreadyExistsException extends ConflictException {
    public FavouriteProductAlreadyExistsException(Long productId) {
        super("Favourite product with id " + productId + " already exists");
    }
}
