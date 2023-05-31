package ua.rivnegray.boardgames_shop.exceptions;

public class RoleNameNotFoundException extends RuntimeException {
    public RoleNameNotFoundException(String name) {
        super("Role with name " + name + " not found");
    }
}
