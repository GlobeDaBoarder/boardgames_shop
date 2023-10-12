package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import ua.rivnegray.boardgames_shop.model.User;

public class UserNotFoundException extends ResourceNotFoundException{
    public UserNotFoundException(String resourceName) {
        super(User.class.toString(), resourceName);
    }
}
