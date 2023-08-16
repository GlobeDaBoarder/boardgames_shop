package ua.rivnegray.boardgames_shop.exceptions.badRequestExceptions;

public class FilterRequestDeserializationException extends BadRequestException {

    public FilterRequestDeserializationException(Throwable cause) {
        super("Can't convert a filter request query parameter to an actual filter.", cause);
    }
}
