package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ua.rivnegray.boardgames_shop.model.BoardGame;

import java.util.Collection;
import java.util.List;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long>, JpaSpecificationExecutor<BoardGame> {
    List<BoardGame> findAllByIsRemovedIsFalse();
    List<BoardGame> findAllByIsRemovedIsTrue();
    List<BoardGame> findAllByProductNameContainingIgnoreCase(String productName);

    List<BoardGame> findAllByProductDescriptionContainingIgnoreCase(String productDescription);

    List<BoardGame> findAllByProductDescriptionContainingIgnoreCaseAndIdNotIn(String productDescription, List<Long> ids);
}
