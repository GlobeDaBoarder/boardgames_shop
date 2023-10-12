package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import ua.rivnegray.boardgames_shop.model.User;

public class UserIdNotFoundException extends ResourceIdNotFoundException {
    public UserIdNotFoundException(Long id) {
        super(User.class.getName(), id);
    }
}
