package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ResourceNotFoundException extends RuntimeException{
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceNotFoundException.class);
    public ResourceNotFoundException(String className, String resourceName) {
        super(className + "name not found");
        LOGGER.debug(className + "with name" + resourceName+ " not found");
    }
}
