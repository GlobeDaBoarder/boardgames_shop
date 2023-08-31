package ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions;

public class ImageFileSaveException extends InternalServerException{
    public ImageFileSaveException(String imageName, Throwable cause) {
        super("Error saving image file " + imageName, cause);
    }
}
