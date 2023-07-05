package ua.rivnegray.boardgames_shop.DTO.request.create;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.URL;
import ua.rivnegray.boardgames_shop.model.BoardGameLanguage;
import ua.rivnegray.boardgames_shop.model.ProductCategory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.BoardGame}
 */

// todo remove product category
public record CreateAndUpdateBoardGameDto(String manufacturer, String productName, String productDescription, BigDecimal productPrice,
                                          Integer productQuantityInStock, String productImageURL, ProductCategory productCategory,
                                          String productCode,  String gameSet,  @Schema(example = "[ 1 ]") Set<Long> gameGenreIds,
                                          @Schema(example = " [ 1 ]") Set<Long> gameMechanicIds, Integer minAge, Integer minPlayers,
                                          String gameDuration, BoardGameLanguage gameLanguage,
                                          @URL @Schema(example = "https://boardgamegeek.com/boardgame/13/catan") String BGGLink) implements Serializable {
}