package ua.rivnegray.boardgames_shop.exceptions.conflictExceptions;

public class ImageAlreadyExistsException extends ConflictException {

    public ImageAlreadyExistsException(String imageHash) {
        super("image", imageHash);
    }
}
