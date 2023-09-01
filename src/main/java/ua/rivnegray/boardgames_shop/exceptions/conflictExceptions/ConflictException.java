package ua.rivnegray.boardgames_shop.exceptions.conflictExceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ConflictException extends RuntimeException{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConflictException.class);
    public ConflictException(String parameterName, String parameterValue) {
        super("Such " + parameterName + " already exists");
        LOGGER.debug(parameterName + " with value" + parameterValue + " already exists");
    }

    public ConflictException(String genericMessage) {
        super(genericMessage);
        LOGGER.debug(genericMessage);
    }
}
