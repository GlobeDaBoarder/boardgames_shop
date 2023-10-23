package ua.rivnegray.boardgames_shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.rivnegray.boardgames_shop.DTO.response.MinMaxDto;
import ua.rivnegray.boardgames_shop.model.BoardGame;

import java.util.List;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long>, JpaSpecificationExecutor<BoardGame> {
    List<BoardGame> findAllByIsRemovedIsTrue();

    @Query(
        """
        SELECT bg FROM BoardGame bg
        WHERE bg.isRemoved = false
        AND (UPPER(bg.productName) LIKE UPPER(CONCAT('%', :searchValue, '%'))
            OR UPPER(bg.productDescription) LIKE UPPER(CONCAT('%', :searchValue, '%')))
        ORDER BY
        CASE WHEN UPPER(bg.productName) LIKE UPPER(CONCAT('%', :searchValue, '%')) THEN 1 ELSE 2 END
        """
    )
    Page<BoardGame> findAllByProductNameAndProductDescriptionContainingIgnoreCaseAndIsRemovedIsFalse(@Param("searchValue") String searchValue, Pageable pageable);

    @Query(
        """
        SELECT new ua.rivnegray.boardgames_shop.DTO.response.MinMaxDto(
                MIN(bg.productPrice),
                MAX(bg.productPrice)
            )
        FROM BoardGame bg
        WHERE bg.isRemoved = false
        """
    )
    MinMaxDto findMinMaxPrice();

    @Query(
            """
            SELECT new ua.rivnegray.boardgames_shop.DTO.response.MinMaxDto(
                    MIN(bg.minGameDuration),
                    MAX(bg.maxGameDuration)
                )
            FROM BoardGame bg
            WHERE bg.isRemoved = false
            """
    )
    MinMaxDto findMinMaxGameDuration();

    List<BoardGame> findTop5ByIsRemovedIsFalseOrderByDateCreatedDesc();
}
