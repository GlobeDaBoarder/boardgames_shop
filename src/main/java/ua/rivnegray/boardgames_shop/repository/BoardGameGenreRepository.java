package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.rivnegray.boardgames_shop.model.BoardGameGenre;

public interface BoardGameGenreRepository extends JpaRepository<BoardGameGenre, Long>    {
}
