package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.rivnegray.boardgames_shop.model.BoardGameMechanic;

public interface BoardGameMechanicRepository extends JpaRepository<BoardGameMechanic, Long> {
}
