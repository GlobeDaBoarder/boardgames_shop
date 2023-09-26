package ua.rivnegray.boardgames_shop.DTO.response;

import lombok.Builder;

@Builder
public record IntermediateRegisterResponseDto(TokenDto token, Long userId) {
}
