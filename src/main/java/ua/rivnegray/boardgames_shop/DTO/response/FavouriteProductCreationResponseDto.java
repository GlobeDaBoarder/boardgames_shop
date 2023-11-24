package ua.rivnegray.boardgames_shop.DTO.response;

import lombok.Builder;

@Builder
public record FavouriteProductCreationResponseDto(
        Long id,
        Long userId,
        Long productId
) {
}
