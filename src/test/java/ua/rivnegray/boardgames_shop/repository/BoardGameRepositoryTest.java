package ua.rivnegray.boardgames_shop.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import ua.rivnegray.boardgames_shop.model.BoardGame;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BoardGameRepositoryTest {
    @Autowired
    private BoardGameRepository boardGameRepositoryUnderTest;

    @Autowired
    private TestEntityManager entityManager;

    private final PageRequest pageRequest = PageRequest.of(0, 10);

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    public void BoardGameRepository_findAllByIsRemovedIsTrue_ReturnsArchivedBoardGame(){
        //Arrange
        BoardGame nonArchivedBoardGame = BoardGame.builder()
                .productName("Test1")
                .productDescription("Test1")
                .productPrice(BigDecimal.valueOf(100))
                .productQuantityInStock(2)
                .build();

        BoardGame archivedBoardGame = BoardGame.builder()
                .productName("Test2")
                .productDescription("Test2")
                .productPrice(BigDecimal.valueOf(100))
                .productQuantityInStock(2)
                .build();
        archivedBoardGame.setIsRemoved(true);

        entityManager.persist(nonArchivedBoardGame);
        entityManager.persistAndFlush(archivedBoardGame);

        //Act
        List<BoardGame> archivedBoardGames = boardGameRepositoryUnderTest.findAllByIsRemovedIsTrue();

        //Assert
        assertThat(archivedBoardGames).isNotNull();
        assertThat(archivedBoardGames).isNotEmpty();
        assertThat(archivedBoardGames).hasSize(1);
        assertThat(archivedBoardGames.get(0).getId()).isEqualTo(archivedBoardGame.getId());
    }

    @Test
    public void BoardGameRepository_findAllByIsRemovedIsTrue_ReturnsEmptyListOfBoardGames(){
        //Arrange
        BoardGame nonArchivedBoardGame = BoardGame.builder()
                .productName("Test1")
                .productDescription("Test1")
                .productPrice(BigDecimal.valueOf(100))
                .productQuantityInStock(2)
                .build();

        BoardGame nonArchivedBoardGame2 = BoardGame.builder()
                .productName("Test2")
                .productDescription("Test2")
                .productPrice(BigDecimal.valueOf(100))
                .productQuantityInStock(2)
                .build();

        entityManager.persist(nonArchivedBoardGame);
        entityManager.persistAndFlush(nonArchivedBoardGame2);

        //Act
        List<BoardGame> archivedBoardGames = boardGameRepositoryUnderTest.findAllByIsRemovedIsTrue();

        //Assert
        assertThat(archivedBoardGames).isNotNull();
        assertThat(archivedBoardGames).isEmpty();
    }

    @Test
    public void BoardGameRepository_findAllByProductNameAndProductDescriptionContainingIgnoreCaseAndIsRemovedIsFalse_ReturnsTitleMatch(){
        //Arrange
        BoardGame boardGame1 = BoardGame.builder()
                .productName("match")
                .productDescription("Test1")
                .productPrice(BigDecimal.valueOf(100))
                .productQuantityInStock(2)
                .build();

        BoardGame boardGame2 = BoardGame.builder()
                .productName("Test2")
                .productDescription("Test2")
                .productPrice(BigDecimal.valueOf(100))
                .productQuantityInStock(2)
                .build();

        entityManager.persist(boardGame1);
        entityManager.persistAndFlush(boardGame2);

        // Act
        List<BoardGame> foundBoardGames = boardGameRepositoryUnderTest.findAllByProductNameAndProductDescriptionContainingIgnoreCaseAndIsRemovedIsFalse("match", pageRequest).toList();

        // Assert
        assertThat(foundBoardGames).isNotNull();
        assertThat(foundBoardGames).isNotEmpty();
        assertThat(foundBoardGames).hasSize(1);
        assertThat(foundBoardGames.get(0).getId()).isEqualTo(boardGame1.getId());
    }

    @Test
    public void BoardGameRepository_findAllByProductNameAndProductDescriptionContainingIgnoreCaseAndIsRemovedIsFalse_ReturnsDescriptionMatch(){
        //Arrange
        BoardGame boardGame1 = BoardGame.builder()
                .productName("Test1")
                .productDescription("match")
                .productPrice(BigDecimal.valueOf(100))
                .productQuantityInStock(2)
                .build();

        BoardGame boardGame2 = BoardGame.builder()
                .productName("Test2")
                .productDescription("Test2")
                .productPrice(BigDecimal.valueOf(100))
                .productQuantityInStock(2)
                .build();

        entityManager.persist(boardGame1);
        entityManager.persistAndFlush(boardGame2);

        // Act
        List<BoardGame> foundBoardGames = boardGameRepositoryUnderTest.findAllByProductNameAndProductDescriptionContainingIgnoreCaseAndIsRemovedIsFalse("match", pageRequest).toList();

        // Assert
        assertThat(foundBoardGames).isNotNull();
        assertThat(foundBoardGames).isNotEmpty();
        assertThat(foundBoardGames).hasSize(1);
        assertThat(foundBoardGames.get(0).getId()).isEqualTo(boardGame1.getId());
    }

    @Test
    public void BoardGameRepository_findAllByProductNameAndProductDescriptionContainingIgnoreCaseAndIsRemovedIsFalse_ReturnsDescriptionAndTitleMatchesInOrder(){
        //Arrange
        BoardGame boardGame1 = BoardGame.builder()
                .productName("Test1")
                .productDescription("match")
                .productPrice(BigDecimal.valueOf(100))
                .productQuantityInStock(2)
                .build();

        BoardGame boardGame2 = BoardGame.builder()
                .productName("match")
                .productDescription("Test2")
                .productPrice(BigDecimal.valueOf(100))
                .productQuantityInStock(2)
                .build();

        entityManager.persist(boardGame1);
        entityManager.persistAndFlush(boardGame2);

        // Act
        List<BoardGame> foundBoardGames = boardGameRepositoryUnderTest.findAllByProductNameAndProductDescriptionContainingIgnoreCaseAndIsRemovedIsFalse("match", pageRequest).toList();

        // Assert
        assertThat(foundBoardGames).isNotNull();
        assertThat(foundBoardGames).isNotEmpty();
        assertThat(foundBoardGames).hasSize(2);
        assertThat(foundBoardGames).extracting(BoardGame::getId).containsExactly(boardGame2.getId(), boardGame1.getId());
    }
}
