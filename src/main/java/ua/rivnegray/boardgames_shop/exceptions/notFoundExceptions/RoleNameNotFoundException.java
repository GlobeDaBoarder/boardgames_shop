package ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions;

public class RoleNameNotFoundException extends ResourceNotFoundException {
    public RoleNameNotFoundException(String name) {
        super("Role with name " + name + " not found");
    }
}
