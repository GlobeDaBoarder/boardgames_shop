package ua.rivnegray.boardgames_shop.model.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserBuilder {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String imageUrl;
//    private Set<UserRole> roles;

    private Map<UserRole, Set<UserPermission>> roleWithPermissions;

    public UserBuilder() {
        this.roleWithPermissions = new HashMap<>();
//        this.roles = new HashSet<>();
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public UserBuilder permission(UserRole userRole, UserPermission permission) {
        if (this.roleWithPermissions.containsKey(userRole)) {
            this.roleWithPermissions.get(userRole).add(permission);
        } else {
            this.roleWithPermissions.put(userRole, new HashSet<>(Set.of(permission)));
        }
//        this.roles.

        return this;
    }

    public User build() {
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setImageUrl(imageUrl);

        for (UserRole role : roleWithPermissions.keySet()) {
            role.setPermissions(roleWithPermissions.get(role));
            user.addRole(role);
//            for (UserPermission permission : roleWithPermissions.get(role)) {
//                user.setPermission(permission);
//            }
        }

        return user;
    }
}
