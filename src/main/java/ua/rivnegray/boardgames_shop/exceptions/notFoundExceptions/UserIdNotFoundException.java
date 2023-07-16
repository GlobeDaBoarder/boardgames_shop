package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

public class UserIdNotFoundException extends ResourceIdNotFoundException {
    public UserIdNotFoundException(Long id) {
        super("User with id " + id + " not found");
    }
}
