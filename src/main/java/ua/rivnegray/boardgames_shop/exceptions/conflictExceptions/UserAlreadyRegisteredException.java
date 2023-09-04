package ua.rivnegray.boardgames_shop.exceptions.conflictExceptions;

public class UserAlreadyRegisteredException extends ConflictException {
    public UserAlreadyRegisteredException(String genericMessage) {
        super(genericMessage);
    }

    public UserAlreadyRegisteredException(){
        super("User with those credentials already exists. Please log in or use different credentials.");
    }
}
