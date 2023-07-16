package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

public abstract class ResourceIdNotFoundException extends RuntimeException {
    public ResourceIdNotFoundException(String message) {
        super(message);
    }
}
