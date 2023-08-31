package ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions;

public class ImageFileDeleteException extends InternalServerException{
    public ImageFileDeleteException(String imagePath, Throwable cause) {
        super("Error deleting image file " + imagePath, cause);
    }
}
