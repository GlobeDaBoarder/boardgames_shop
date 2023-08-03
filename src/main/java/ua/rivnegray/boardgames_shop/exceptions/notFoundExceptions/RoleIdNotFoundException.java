package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

import ua.rivnegray.boardgames_shop.model.UserRole;

public class RoleIdNotFoundException extends ResourceIdNotFoundException{
    public RoleIdNotFoundException(Long id) {
        super(UserRole.class.getName(), id);
    }
}
