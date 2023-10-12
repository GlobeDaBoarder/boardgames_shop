package ua.rivnegray.boardgames_shop.exceptions.conflictExceptions;

public class PasswordMissMatchException extends ConflictException{
    public PasswordMissMatchException() {
        super("old password provided is not correct. They do not match. Please try again.");
    }
}
