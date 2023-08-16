package ua.rivnegray.boardgames_shop.exceptions.badRequestExceptions;

public abstract class BadRequestException extends RuntimeException{
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
