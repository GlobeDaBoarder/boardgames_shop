package ua.rivnegray.boardgames_shop.model;

import org.springframework.data.domain.Sort;

public enum SortType {

    PRICE_ASC(Sort.Direction.ASC, "productPrice"),
    PRICE_DESC(Sort.Direction.DESC, "productPrice"),
    NAME_ASC(Sort.Direction.ASC, "productName"),
    NAME_DESC(Sort.Direction.DESC, "productName"),
    NEWEST(Sort.Direction.DESC, "dateCreated");

    private final Sort.Direction direction;
    private final String property;

    SortType(Sort.Direction direction, String property) {
        this.direction = direction;
        this.property = property;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public String getProperty() {
        return property;
    }
}

