package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import ua.rivnegray.boardgames_shop.model.UserProfile;

public class UserIdNotFoundException extends ResourceIdNotFoundException {
    public UserIdNotFoundException(Long id) {
        super(UserProfile.class.getName(), id);
    }
}
