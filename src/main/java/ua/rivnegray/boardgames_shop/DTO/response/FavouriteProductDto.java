package ua.rivnegray.boardgames_shop.DTO.response;

import lombok.Builder;

@Builder
public record FavouriteProductDto(
        Long id,
        BoardGameSummaryDto boardGame
) {
}
