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
    public void findAllByIsRemovedIsTrue_ReturnsArchivedBoardGame(){
        //Arrange
        BoardGame nonArchivedBoardGame = createTestGame("Test1", "Test1");

        BoardGame archivedBoardGame = createTestGame("Test2", "Test2", true);

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
    public void findAllByIsRemovedIsTrue_ReturnsEmptyListOfBoardGames(){
        //Arrange
        BoardGame nonArchivedBoardGame = createTestGame("Test1", "Test1");

        BoardGame nonArchivedBoardGame2 = createTestGame("Test2", "Test2");

        entityManager.persist(nonArchivedBoardGame);
        entityManager.persistAndFlush(nonArchivedBoardGame2);

        //Act
        List<BoardGame> archivedBoardGames = boardGameRepositoryUnderTest.findAllByIsRemovedIsTrue();

        //Assert
        assertThat(archivedBoardGames).isNotNull();
        assertThat(archivedBoardGames).isEmpty();
    }

    @Test
    public void findAllByProductNameAndProductDescriptionContainingIgnoreCaseAndIsRemovedIsFalse_ReturnsTitleMatch(){
        //Arrange
        BoardGame boardGame1 = createTestGame("match", "Test1");

        BoardGame boardGame2 = createTestGame("Test2", "Test2");

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
    public void findAllByProductNameAndProductDescriptionContainingIgnoreCaseAndIsRemovedIsFalse_ReturnsDescriptionMatch(){
        //Arrange
        BoardGame boardGame1 = createTestGame("Test1", "match");

        BoardGame boardGame2 = createTestGame("Test2", "Test2");

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
    public void findAllByProductNameAndProductDescriptionContainingIgnoreCaseAndIsRemovedIsFalse_ReturnsDescriptionAndTitleMatchesInOrder(){
        //Arrange
        BoardGame boardGame1 = createTestGame(("Test1"), "match");

        BoardGame boardGame2 = createTestGame("match", "Test2");

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

    private BoardGame createTestGame(String name, String description, boolean isRemoved){
        BoardGame bg =  BoardGame.builder()
                .productName(name)
                .productDescription(description)
                .productPrice(BigDecimal.valueOf(100))
                .productQuantityInStock(2)
                .build();

        bg.setIsRemoved(isRemoved);

        return bg;
    }
    private BoardGame createTestGame(String name, String description){
        return createTestGame(name, description, false);
    }

}
