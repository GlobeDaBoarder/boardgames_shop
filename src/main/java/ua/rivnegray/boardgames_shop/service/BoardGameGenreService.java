package ua.rivnegray.boardgames_shop.service;

import org.springframework.transaction.annotation.Transactional;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameGenreDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameGenreDto;

import java.util.List;

public interface BoardGameGenreService {
    @Transactional(readOnly = true)
    List<BoardGameGenreDto> getAllGenres();

    @Transactional(readOnly = true)
    BoardGameGenreDto getGenreById(Long id);

    @Transactional
    BoardGameGenreDto addGenre(CreateAndUpdateBoardGameGenreDto createAndUpdateBoardGameGenreDto);

    @Transactional
    BoardGameGenreDto updateGenre(Long id, CreateAndUpdateBoardGameGenreDto createAndUpdateBoardGameGenreDto);

    @Transactional
    void deleteGenre(Long id);
}
