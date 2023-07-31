package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

public class RoleIdNotFoundException extends ResourceIdNotFoundException{
    public RoleIdNotFoundException(Long id) {
        super("Role id not found");
    }
}
