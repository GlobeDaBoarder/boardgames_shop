package ua.rivnegray.boardgames_shop.DTO.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@Getter
@EqualsAndHashCode
@ToString
public final class FilterCategoryWithArrayDataDto {
    private final String nameCategory;
    private final List<String> nameFilters;
    private final boolean isScrolled = false;

    @Builder
    public FilterCategoryWithArrayDataDto(String nameCategory, List<String> nameFilters) {
        this.nameCategory = nameCategory;
        this.nameFilters = nameFilters;
    }
}
