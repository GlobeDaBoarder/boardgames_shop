package ua.rivnegray.boardgames_shop.DTO.response;


import lombok.Builder;

import java.util.List;

@Builder
public record FilterCategoryWithArrayDataDto(String nameCategory, List<String> nameFilters) {
}
