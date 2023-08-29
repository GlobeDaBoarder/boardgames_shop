package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import ua.rivnegray.boardgames_shop.model.ProductImage;

public class ImageNotFoundException extends ResourceNotFoundException {

    public ImageNotFoundException( String imageUrl) {
        super(ProductImage.class.getName(), imageUrl);
    }
}
