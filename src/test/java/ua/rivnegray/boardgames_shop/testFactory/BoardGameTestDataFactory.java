package ua.rivnegray.boardgames_shop.testFactory;

import org.springframework.test.util.ReflectionTestUtils;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameGenreDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameMechanicDto;
import ua.rivnegray.boardgames_shop.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class BoardGameTestDataFactory {
    private static final LocalDateTime NOW = LocalDateTime.now();
    public static BoardGameDto createResultBoardgameDto(){
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
                .dateCreated(NOW)
                .dateUpdated(NOW)
                .dateCreated(NOW)
                .dateUpdated(NOW)
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
                .gameLanguage(BoardGameLanguage.ENGLISH.getLanguageNameInUkrainian())
                .BGGLink("https://boardgamegeek.com/boardgame/13/catan")
                .build();
    }

    public static CreateAndUpdateBoardGameDto createCreateAndUpdateDto() {
        return CreateAndUpdateBoardGameDto.builder()
                .manufacturer("Test")
                .productName("Test")
                .productDescription("Test")
                .productPrice(BigDecimal.valueOf(100))
                .productQuantityInStock(1)
                .productImageURLs(Set.of("/boardgames/images/15.png", "/boardgames/images/16.png"))
                .productCode("Test")
                .gameSet("Test")
                .gameGenreIds(Set.of(1L, 2L))
                .gameMechanicIds(Set.of(1L, 2L))
                .minAge(6)
                .minPlayers(3)
                .maxPlayers(6)
                .minGameDuration(30)
                .maxGameDuration(60)
                .gameLanguage(BoardGameLanguage.ENGLISH.getLanguageNameInUkrainian())
                .BGGLink("https://boardgamegeek.com/boardgame/13/catan")
                .build();
    }

    public static BoardGame createTestBoardGame(){
        BoardGameGenre genre1 = new BoardGameGenre("Test", "Test");
        ReflectionTestUtils.setField(genre1, "id", 1L);
        ReflectionTestUtils.setField(genre1, "dateCreated", NOW);
        ReflectionTestUtils.setField(genre1, "dateUpdated", NOW);

        BoardGameGenre genre2 = new BoardGameGenre("Test2", "Test2");
        ReflectionTestUtils.setField(genre2, "id", 2L);
        ReflectionTestUtils.setField(genre2, "dateCreated", NOW);
        ReflectionTestUtils.setField(genre2, "dateUpdated", NOW);

        BoardGameMechanic mechanic1 = new BoardGameMechanic("Test", "Test");
        ReflectionTestUtils.setField(mechanic1, "id", 1L);
        ReflectionTestUtils.setField(mechanic1, "dateCreated", NOW);
        ReflectionTestUtils.setField(mechanic1, "dateUpdated", NOW);

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
}
