package ua.rivnegray.boardgames_shop.exceptions.conflictExceptions;

public class UsernameAlreadyTakenException extends ConflictException{
    public UsernameAlreadyTakenException(String message) {
        super(message);
    }
}
