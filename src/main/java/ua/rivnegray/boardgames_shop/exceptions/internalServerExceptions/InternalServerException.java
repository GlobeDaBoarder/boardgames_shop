package ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions;

public abstract class InternalServerException extends RuntimeException{
    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
