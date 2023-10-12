package ua.rivnegray.boardgames_shop.DTO.request.create;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.hibernate.validator.constraints.URL;
import ua.rivnegray.boardgames_shop.model.BoardGameLanguage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.BoardGame}
 */
@Builder
public record CreateAndUpdateBoardGameDto(
        String manufacturer,
        String productName,
        String productNameInEnglish,
        String productDescription,
        BigDecimal productPrice,
        Integer productQuantityInStock,
        @Schema(example = "[ \"/boardgames/images/15.png\" ]")
        Set<String> productImageURLs,
        String productCode,
        String gameSet,
        @Schema(example = "[ \"Стратегії\", \"Економічні\" ]")
        Set<String> gameTypeNames,
        @Schema(example = "[ 1 ]")
        Set<Long> gameGenreIds,
        @Schema(example = " [ 1 ]")
        Set<Long> gameMechanicIds,
        Integer minAge,
        Integer minPlayers,
        Integer maxPlayers,
        Integer minGameDuration,
        Integer maxGameDuration,
        String author,
        String illustrator,
        BoardGameLanguage gameLanguage,
        @URL
        @Schema(example = "https://boardgamegeek.com/boardgame/13/catan")
        String BGGLink) implements Serializable {
}

