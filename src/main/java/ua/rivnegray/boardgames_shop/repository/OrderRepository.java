package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.rivnegray.boardgames_shop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
