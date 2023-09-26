package ua.rivnegray.boardgames_shop.exceptions.badRequestExceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.ConflictException;

public abstract class BadRequestException extends RuntimeException{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConflictException.class);

    public BadRequestException(String message, Throwable cause){
        super(message, cause);
        LOGGER.debug(cause.getMessage());
    }
}
