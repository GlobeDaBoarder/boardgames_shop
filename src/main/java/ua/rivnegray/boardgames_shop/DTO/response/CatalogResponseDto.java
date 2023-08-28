package ua.rivnegray.boardgames_shop.DTO.response;

import java.util.List;

public record CatalogResponseDto(
        List<BoardGameSummaryDto> boardGames,
        Integer totalPages
) { }
