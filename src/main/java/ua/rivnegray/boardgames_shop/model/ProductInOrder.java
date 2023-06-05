package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductInOrder extends BaseEntity{
    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;

    private Integer quantity;
}
