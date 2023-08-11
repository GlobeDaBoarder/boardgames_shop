package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.rivnegray.boardgames_shop.model.ProductInOrder;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {
}
