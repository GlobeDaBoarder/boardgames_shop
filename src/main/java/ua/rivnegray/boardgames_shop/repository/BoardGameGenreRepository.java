package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.rivnegray.boardgames_shop.model.BoardGameGenre;

import java.util.Optional;

public interface BoardGameGenreRepository extends JpaRepository<BoardGameGenre, Long>    {
    Optional<BoardGameGenre> findByGenreName(String genreName);
}
