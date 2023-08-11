package ua.rivnegray.boardgames_shop.DTO.request;

import ua.rivnegray.boardgames_shop.model.BoardGameLanguage;

import java.math.BigDecimal;
import java.util.Set;

public record FilterBoardGamesRequestDto(Set<String> manufacturers, BigDecimal minProductPrice, BigDecimal maxProductPrice,
                                         Set<String> boardGameGenres, Set<String> boardGameMechanics, Set<Integer> minAges,
                                         Set<Integer> playerCounts,
                                         Integer minGameDuration, Integer maxGameDuration,
                                         Set<BoardGameLanguage> boardGameLanguages) {
}
