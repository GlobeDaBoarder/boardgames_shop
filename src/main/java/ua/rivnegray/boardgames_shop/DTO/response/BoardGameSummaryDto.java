package ua.rivnegray.boardgames_shop.DTO.response;

import java.math.BigDecimal;

public record BoardGameSummaryDto(Long id, String productName, BigDecimal productPrice,
                                  Integer productQuantityInStock, String productImageURL) {
}
