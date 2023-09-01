package ua.rivnegray.boardgames_shop.exceptions.conflictExceptions;

public class TwoUserProfilesFoundException extends ConflictException {
    public TwoUserProfilesFoundException(String genericMessage) {
        super(genericMessage);
    }

    public TwoUserProfilesFoundException(){
        super("""
    There were 2 separate user profiles found: one matching an email specified, and one matching a phone number specified.
     Please, make sure the information you entered is correct and try again. If the issue persists, please, contact the support.
     """);
    }
}

