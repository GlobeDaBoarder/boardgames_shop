package ua.rivnegray.boardgames_shop.exceptions.conflictExceptions;

public class PhoneAlreadyInUseException extends ConflictException {
    public PhoneAlreadyInUseException(String phone) {
        super("phone", phone);
    }
}
