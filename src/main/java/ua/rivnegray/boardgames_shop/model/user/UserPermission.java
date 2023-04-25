package ua.rivnegray.boardgames_shop.model.user;

import org.springframework.security.core.GrantedAuthority;

public enum UserPermission implements GrantedAuthority {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public String getAuthority() {
        return permission;
    }
}
