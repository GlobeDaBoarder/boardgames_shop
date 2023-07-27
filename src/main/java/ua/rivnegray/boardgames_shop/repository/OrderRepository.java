package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.rivnegray.boardgames_shop.model.Order;

import java.util.Optional;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdAndUserProfile_Id(Long orderId, Long userId);
    Set<Order> findAllByUserProfile_Id(Long userId);
}
