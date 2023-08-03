package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import ua.rivnegray.boardgames_shop.model.UserRole;

public class RoleNameNotFoundException extends ResourceNotFoundException {
    public RoleNameNotFoundException(String name) {
        super(UserRole.class.getName(), name);
    }
}
