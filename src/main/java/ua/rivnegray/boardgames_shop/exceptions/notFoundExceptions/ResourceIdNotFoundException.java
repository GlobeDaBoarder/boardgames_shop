package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ResourceIdNotFoundException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceIdNotFoundException.class);
    public ResourceIdNotFoundException(String className, Long id) {
        super(className + " id not found");
        LOGGER.debug(className + " with id " + id + " not found");
    }
}
