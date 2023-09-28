package ua.rivnegray.boardgames_shop.DTO.response;

import lombok.Builder;

@Builder
public record FilterDataDto(FilterArrayCategoriesDto filters, Number absoluteMinPrice, Number absoluteMaxPrice,
                            Number absoluteMinGameDuration, Number absoluteMaxGameDuration) {
}
