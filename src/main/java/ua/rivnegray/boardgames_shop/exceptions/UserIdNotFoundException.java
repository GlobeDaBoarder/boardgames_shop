package ua.rivnegray.boardgames_shop.exceptions;

public class UserIdNotFoundException extends RuntimeException {
    public UserIdNotFoundException(Long id) {
        super("User with id " + id + " not found");
    }
}
