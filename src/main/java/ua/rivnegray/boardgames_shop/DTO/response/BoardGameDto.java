package ua.rivnegray.boardgames_shop.DTO.response;

import lombok.Builder;
import org.hibernate.validator.constraints.URL;
import ua.rivnegray.boardgames_shop.model.ProductCategory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.BoardGame}
 */
@Builder
public record BoardGameDto(Long id,
                           LocalDateTime dateCreated,
                           LocalDateTime dateUpdated,
                           Boolean isRemoved,
                           String manufacturer,
                           String productName,
                           String productNameInEnglish,
                           String productDescription,
                           BigDecimal productPrice,
                           Integer productQuantityInStock,
                           Set<String> productImageURLs,
                           ProductCategory productCategory,
                           String productCode,
                           String gameSet,
                               Set<String> gameTypes,
                           Set<BoardGameGenreDto> gameGenres,
                           Set<BoardGameMechanicDto> gameMechanics,
                           Integer minAge,
                           Integer minPlayers,
                           Integer maxPlayers,
                           Integer minGameDuration,
                           Integer maxGameDuration,
                           String author,
                           String illustrator,
                           String gameLanguage,
                           @URL String BGGLink) implements Serializable {
}
