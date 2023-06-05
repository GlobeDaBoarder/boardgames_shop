package ua.rivnegray.boardgames_shop.exceptions;

public class RoleIdNotFoundException extends RuntimeException{
    public RoleIdNotFoundException(Long id) {
        super("Role with id " + id + " not found");
    }
}
