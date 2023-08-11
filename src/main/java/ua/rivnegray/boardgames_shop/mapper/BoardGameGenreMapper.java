package ua.rivnegray.boardgames_shop.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameGenreDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameGenreDto;
import ua.rivnegray.boardgames_shop.model.BoardGameGenre;

@Mapper(componentModel = "spring")
public interface BoardGameGenreMapper {
    BoardGameGenreDto boardGameGenreToBoardGameGenreDto(BoardGameGenre boardGameGenre);

    BoardGameGenre createBoardGameGenreDtoToBoardGameGenre(CreateAndUpdateBoardGameGenreDto createAndUpdateBoardGameGenreDto);

    void updateBoardGameGenreFromDto(CreateAndUpdateBoardGameGenreDto createAndUpdateBoardGameGenreDto,
                                     @MappingTarget BoardGameGenre boardGameGenre);
}
