package ua.rivnegray.boardgames_shop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameGenreDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameMechanicDto;
import ua.rivnegray.boardgames_shop.mapper.BoardGameMapper;
import ua.rivnegray.boardgames_shop.model.BoardGame;
import ua.rivnegray.boardgames_shop.model.BoardGameGenre;
import ua.rivnegray.boardgames_shop.model.BoardGameLanguage;
import ua.rivnegray.boardgames_shop.model.BoardGameMechanic;
import ua.rivnegray.boardgames_shop.model.ProductCategory;
import ua.rivnegray.boardgames_shop.repository.BoardGameGenreRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameMechanicRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BoardGameServiceImplTest {
    @Mock
    private BoardGameRepository boardGameRepository;
    @Mock
    private BoardGameMapper boardGameMapper;
    @Mock
    private BoardGameGenreRepository boardGameGenreRepository;
    @Mock
    private BoardGameMechanicRepository boardGameMechanicRepository;
    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private BoardGameServiceImpl boardGameServiceUnderTest;

    private LocalDateTime now;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        now = LocalDateTime.now();
        objectMapper = new ObjectMapper();
    }


    @Test
    void addBoardGame_returnsBoardGameDto() {
        //Arrange
        CreateAndUpdateBoardGameDto createAndUpdateBoardGameDto = CreateAndUpdateBoardGameDto.builder()
                .manufacturer("Test")
                .productName("Test")
                .productDescription("Test")
                .productPrice(BigDecimal.valueOf(100))
                .productQuantityInStock(1)
                .productImageURLs(Set.of("/boardgames/images/15.png", "/boardgames/images/16.png"))
                .productCategory(ProductCategory.BOARD_GAMES)
                .productCode("Test")
                .gameSet("Test")
                .gameGenreIds(Set.of(1L, 2L))
                .gameMechanicIds(Set.of(1L, 2L))
                .minAge(6)
                .minPlayers(3)
                .maxPlayers(6)
                .minGameDuration(30)
                .maxGameDuration(60)
                .gameLanguage(BoardGameLanguage.ENGLISH)
                .BGGLink("https://boardgamegeek.com/boardgame/13/catan")
                .build();

        BoardGame boardGameState = createTestBoardGame();


        BoardGameDto resultBoardGameDto = createResultBoardgameDto();


        when(boardGameMapper.createBoardGameDtoToBoardGame(eq(createAndUpdateBoardGameDto), eq(boardGameGenreRepository), eq(boardGameMechanicRepository)))
                .thenReturn(boardGameState);

        doAnswer(invocationOnMock -> {
            BoardGame boardGame = invocationOnMock.getArgument(0);
            ReflectionTestUtils.setField(boardGame, "id", 1L);
            return boardGame;
        }).when(boardGameRepository).save(eq(boardGameState));

        doAnswer(invocationOnMock -> {
            BoardGame boardGame = invocationOnMock.getArgument(0);
            ReflectionTestUtils.setField(boardGame, "dateCreated", now);
            ReflectionTestUtils.setField(boardGame, "dateUpdated", now);
            return boardGame;
        }).when(entityManager).refresh(any(BoardGame.class));

        when(boardGameMapper.boardGameToBoardGameDto(eq(boardGameState)))
                .thenReturn(resultBoardGameDto);


        //Act
        BoardGameDto result = boardGameServiceUnderTest.addBoardGame(createAndUpdateBoardGameDto);

        //Assert

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(resultBoardGameDto);
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.id()).isEqualTo(boardGameState.getId());
        assertThat(result.dateCreated()).isEqualTo(now);
        assertThat(result.dateUpdated()).isEqualTo(boardGameState.getDateUpdated());
        assertThat(result.gameGenres().stream().map(BoardGameGenreDto::id)).containsExactlyInAnyOrder(1L, 2L);
        assertThat(result.gameMechanics().stream().map(BoardGameMechanicDto::id)).containsExactlyInAnyOrder(1L);
    }

    BoardGame createTestBoardGame(){
        BoardGameGenre genre1 = new BoardGameGenre("Test", "Test");
        ReflectionTestUtils.setField(genre1, "id", 1L);
        ReflectionTestUtils.setField(genre1, "dateCreated", now);
        ReflectionTestUtils.setField(genre1, "dateUpdated", now);

        BoardGameGenre genre2 = new BoardGameGenre("Test2", "Test2");
        ReflectionTestUtils.setField(genre2, "id", 2L);
        ReflectionTestUtils.setField(genre2, "dateCreated", now);
        ReflectionTestUtils.setField(genre2, "dateUpdated", now);

        BoardGameMechanic mechanic1 = new BoardGameMechanic("Test", "Test");
        ReflectionTestUtils.setField(mechanic1, "id", 1L);
        ReflectionTestUtils.setField(mechanic1, "dateCreated", now);
        ReflectionTestUtils.setField(mechanic1, "dateUpdated", now);

        return BoardGame.builder()
                .manufacturer("Test")
                .productName("Test")
                .productDescription("Test")
                .productPrice(BigDecimal.valueOf(100))
                .productQuantityInStock(1)
                .productImageURLs(Set.of("/boardgames/images/15.png", "/boardgames/images/16.png"))
                .productCategory(ProductCategory.BOARD_GAMES)
                .productCode("Test")
                .gameSet("Test")
                .gameGenres(Set.of(genre1, genre2))
                .gameMechanics(Set.of(mechanic1))
                .minAge(6)
                .minPlayers(3)
                .maxPlayers(6)
                .minGameDuration(30)
                .maxGameDuration(60)
                .gameLanguage(BoardGameLanguage.ENGLISH)
                .BGGLink("https://boardgamegeek.com/boardgame/13/catan")
                .build();
    }

    BoardGameDto createResultBoardgameDto(){
        BoardGameGenreDto genre1DTO = BoardGameGenreDto.builder()
                .id(1L)
                .genreName("Test")
                .genreDescription("Test")
                .build();

        BoardGameGenreDto genre2DTO = BoardGameGenreDto.builder()
                .id(2L)
                .genreName("Test2")
                .genreDescription("Test2")
                .build();

        BoardGameMechanicDto mechanic1DTO = BoardGameMechanicDto.builder()
                .id(1L)
                .mechanicName("Test")
                .mechanicDescription("Test")
                .build();

        return BoardGameDto.builder()
                .id(1L)
                .dateCreated(now)
                .dateUpdated(now)
                .dateCreated(now)
                .dateUpdated(now)
                .isRemoved(false)
                .manufacturer("Test")
                .productName("Test")
                .productDescription("Test")
                .productPrice(BigDecimal.valueOf(100))
                .productQuantityInStock(1)
                .productImageURLs(Set.of("/boardgames/images/15.png", "/boardgames/images/16.png"))
                .productCategory(ProductCategory.BOARD_GAMES)
                .productCode("Test")
                .gameSet("Test")
                .gameGenres(Set.of(genre1DTO, genre2DTO))
                .gameMechanics(Set.of(mechanic1DTO))
                .minAge(6)
                .minPlayers(3)
                .maxPlayers(6)
                .minGameDuration(30)
                .maxGameDuration(60)
                .gameLanguage(BoardGameLanguage.ENGLISH)
                .BGGLink("https://boardgamegeek.com/boardgame/13/catan")
                .build();
    }

}
