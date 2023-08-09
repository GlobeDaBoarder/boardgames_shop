package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.rivnegray.boardgames_shop.model.BoardGameMechanic;

import java.util.Optional;

public interface BoardGameMechanicRepository extends JpaRepository<BoardGameMechanic, Long> {
    Optional<BoardGameMechanic> findByMechanicName(String mechanicName);
}
