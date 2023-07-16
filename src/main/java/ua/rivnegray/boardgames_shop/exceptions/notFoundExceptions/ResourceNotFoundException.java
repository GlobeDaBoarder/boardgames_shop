package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

public abstract class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
