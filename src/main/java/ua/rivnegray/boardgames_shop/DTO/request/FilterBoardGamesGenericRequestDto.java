package ua.rivnegray.boardgames_shop.DTO.request;

import java.util.Map;
import java.util.Set;

public record FilterBoardGamesGenericRequestDto(Map<String, Set<String>> filterMap) {
}
