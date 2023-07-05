package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart extends BaseEntity{

    @OneToOne(mappedBy = "shoppingCart")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "shoppingCart")
    private Set<ProductInShoppingCart> productsInShoppingCart = new HashSet<>();
}
