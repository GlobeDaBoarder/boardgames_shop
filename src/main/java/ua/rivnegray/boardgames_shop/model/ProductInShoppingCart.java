package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInShoppingCart extends BaseEntity{
    @ManyToOne
    private Product product;

    @ManyToOne
    private ShoppingCart shoppingCart;

    private Integer quantity;
}
