package ua.rivnegray.boardgames_shop.DTO.response;

import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.BoardGameGenre}
 */
@Builder
public record BoardGameGenreDto(Long id, String genreName, String genreDescription) implements Serializable {
}
