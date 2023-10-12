package ua.rivnegray.boardgames_shop.DTO.request;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Set;

@Builder
public record FilterBoardGamesRequestDto(Set<String> manufacturers, BigDecimal minProductPrice, BigDecimal maxProductPrice,
                                         Set<String> boardGameTypes,
                                         Set<String> boardGameGenres, Set<String> boardGameMechanics, Set<String> ageIntervals,
                                         Set<String> playerCounts,
                                         Integer minGameDuration, Integer maxGameDuration,
                                         Set<String> boardGameLanguages) {
}
