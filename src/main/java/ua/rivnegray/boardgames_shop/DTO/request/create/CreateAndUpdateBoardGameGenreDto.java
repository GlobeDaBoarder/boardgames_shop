package ua.rivnegray.boardgames_shop.DTO.request.create;

import java.io.Serializable;

/**
 * DTO for {@link ua.rivnegray.boardgames_shop.model.BoardGameGenre}
 */
public record CreateAndUpdateBoardGameGenreDto(String genreName, String genreDescription) implements Serializable {
}
