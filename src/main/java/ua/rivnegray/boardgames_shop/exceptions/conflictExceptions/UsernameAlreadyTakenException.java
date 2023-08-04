package ua.rivnegray.boardgames_shop.exceptions.conflictExceptions;

public class UsernameAlreadyTakenException extends ConflictException{
    public UsernameAlreadyTakenException(String parameterValue) {
        super("username", parameterValue);
    }
}
