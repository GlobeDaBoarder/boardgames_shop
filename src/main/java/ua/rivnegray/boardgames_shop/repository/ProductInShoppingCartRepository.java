package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.rivnegray.boardgames_shop.model.ProductInShoppingCart;

@Repository
public interface ProductInShoppingCartRepository extends JpaRepository<ProductInShoppingCart, Long> {
}
