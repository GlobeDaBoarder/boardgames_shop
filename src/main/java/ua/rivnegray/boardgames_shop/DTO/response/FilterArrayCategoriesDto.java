package ua.rivnegray.boardgames_shop.DTO.response;

import lombok.Builder;

@Builder
public record FilterArrayCategoriesDto(FilterCategoryWithArrayDataDto boardGameTypes,
                                       FilterCategoryWithArrayDataDto boardGameGenres,
                                       FilterCategoryWithArrayDataDto boardGameMechanics,
                                       FilterCategoryWithArrayDataDto ageIntervals,
                                       FilterCategoryWithArrayDataDto playerCounts,
                                       FilterCategoryWithArrayDataDto boardGameLanguages) {
}

