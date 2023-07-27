package ua.rivnegray.boardgames_shop.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserPermission implements GrantedAuthority {
    // current user
    USER_READ_ME("user:readMe"),
    USER_UPDATE_ME("user:updateMe"),

    // admin operations on users
    USER_READ("user:read"),
    USER_CREATE("user:create"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete"),


    //boardgames

    BOARD_GAME_CREATE("boardGame:create"),
    BOARD_GAME_UPDATE("boardGame:update"),
    BOARD_GAME_DELETE("boardGame:delete"),

    //genres and mechanics for boardgames

    GENRE_AND_MECHANIC_CREATE("genreAndMechanic:create"),
    GENRE_AND_MECHANIC_UPDATE("genreAndMechanic:update"),
    GENRE_AND_MECHANIC_DELETE("genreAndMechanic:delete"),

    // shopping cart

    SHOPPING_CART_READ("shoppingCart:read"),
    /**
     * This is a general permission for customer user.
     * It allows to do all operations with shopping cart, like add item, remove item, clear, change quanity and checkout
     */
    SHOPPING_CART_MANAGE_ME("shoppingCart:manageMe"),

    //orders of current user

    ORDER_READ_ME("order:readMe"),
    ORDER_CANCEL_ME("order:cancelMe"),

    // admin operations on orders
    ORDER_READ("order:read"),
    ORDER_CREATE("order:create"),
    ORDER_UPDATE_STATUS("order:updateStatus"),
    ORDER_DELETE("order:delete");


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
