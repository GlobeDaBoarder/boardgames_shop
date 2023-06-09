package ua.rivnegray.boardgames_shop.DTO.request.create;

import org.hibernate.validator.constraints.URL;
import ua.rivnegray.boardgames_shop.model.BoardGameLanguage;
import ua.rivnegray.boardgames_shop.model.ProductCategory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.BoardGame}
 */
public record CreateBoardGameDto( String Manufacturer, String productName, String productDescription, BigDecimal productPrice,
                                 Integer productQuantityInStock, String productImageURL, ProductCategory productCategory,
                                 String productCode, String gameSet, Set<String> gameGenreGenreNames,
                                 Set<String> gameMechanicMechanicNames, Integer minAge, Integer minPlayers,
                                 String gameDuration, BoardGameLanguage gameLanguage,
                                 @URL String BGGLink) implements Serializable {
}
