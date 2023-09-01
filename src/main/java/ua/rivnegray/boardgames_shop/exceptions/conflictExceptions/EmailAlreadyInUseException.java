package ua.rivnegray.boardgames_shop.exceptions.conflictExceptions;

public class EmailAlreadyInUseException extends ConflictException {
    public EmailAlreadyInUseException(String genericMessage) {
        super(genericMessage);
    }
}
