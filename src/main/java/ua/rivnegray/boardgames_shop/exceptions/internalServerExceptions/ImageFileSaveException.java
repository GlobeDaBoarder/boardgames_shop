package ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions;

public class ImageFileSaveException extends InternalServerException{
    public ImageFileSaveException(Throwable cause) {
        super("Error saving image file", cause);
    }
}
