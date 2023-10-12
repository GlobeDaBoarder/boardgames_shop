package ua.rivnegray.boardgames_shop.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameGenreDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameMechanicDto;
import ua.rivnegray.boardgames_shop.mapper.BoardGameMapper;
import ua.rivnegray.boardgames_shop.model.BoardGame;
import ua.rivnegray.boardgames_shop.repository.BoardGameGenreRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameMechanicRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;
import ua.rivnegray.boardgames_shop.service.impl.BoardGameServiceImpl;
import ua.rivnegray.boardgames_shop.testFactory.BoardGameTestDataFactory;

import java.time.LocalDateTime;

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
    private CreateAndUpdateBoardGameDto createAndUpdateBoardGameDto;
    private BoardGame boardGameState;
    private BoardGameDto resultBoardGameDto;

    @BeforeEach
    void setUp() {
        this.now = LocalDateTime.now();
        this.createAndUpdateBoardGameDto = BoardGameTestDataFactory.createCreateAndUpdateDto();
        this.boardGameState = BoardGameTestDataFactory.createTestBoardGame();
        this.resultBoardGameDto = BoardGameTestDataFactory. createResultBoardgameDto();
    }

    @Test
    void addBoardGame_returnsBoardGameDto() {

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

    @Test
    void addBoardGame_returnsBoardGameDto2(){
        // Arrange

        CreateAndUpdateBoardGameDto createAndUpdateBoardGameDto = Mockito.mock(CreateAndUpdateBoardGameDto.class);
        BoardGame boardGame = Mockito.mock(BoardGame.class);
        BoardGameDto returnedDto = Mockito.mock(BoardGameDto.class);

        when(boardGameMapper.createBoardGameDtoToBoardGame(any(CreateAndUpdateBoardGameDto.class), any(BoardGameGenreRepository.class), any(BoardGameMechanicRepository.class)))
                .thenReturn(boardGame);
        when(boardGameRepository.save(boardGame))
                .thenReturn(boardGame);
        when(boardGameMapper.boardGameToBoardGameDto(boardGame))
                .thenReturn(returnedDto);

        // Act

        BoardGameDto result = boardGameServiceUnderTest.addBoardGame(createAndUpdateBoardGameDto);

        // Assert

        assertThat(result).isEqualTo(returnedDto);
        assertThat(result).isNotNull();
    }
}
