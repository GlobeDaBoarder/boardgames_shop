package ua.rivnegray.boardgames_shop.DTO.response;

import lombok.Builder;

@Builder
public record FilteringDataDto(FilterCategoryDto boardGameGenres, FilterCategoryDto boardGameMechanics,
                               FilterCategoryDto ageIntervals, FilterCategoryDto playerCounts,
                               FilterCategoryDto boardGameLanguages) {
}

