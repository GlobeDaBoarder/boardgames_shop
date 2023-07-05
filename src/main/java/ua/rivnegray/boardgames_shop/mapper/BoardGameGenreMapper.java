package ua.rivnegray.boardgames_shop.mapper;


import org.mapstruct.Mapper;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameGenreDto;
import ua.rivnegray.boardgames_shop.model.BoardGameGenre;

@Mapper(componentModel = "spring")
public interface BoardGameGenreMapper {
    BoardGameGenreDto boardGameGenreToBoardGameGenreDto(BoardGameGenre boardGameGenre);

    BoardGameGenre boardGameGenreDtoToBoardGameGenre(BoardGameGenreDto boardGameGenreDto);
}
