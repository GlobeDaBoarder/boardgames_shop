package ua.rivnegray.boardgames_shop.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameGenreDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameMechanicDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameGenreDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameMechanicDto;

import java.util.List;

public interface BoardGameService {
    @Transactional(readOnly = true)
    List<BoardGameDto> getAllBoardGames();

    @Transactional
    BoardGameDto addBoardGame(CreateAndUpdateBoardGameDto createBoardGameDto);

    @Transactional(readOnly = true)
    BoardGameDto getBoardGameById(Long id);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    BoardGameDto updateBoardGame(Long id, CreateAndUpdateBoardGameDto updateBoardGameDto);

    @Transactional
    void deleteBoardGame(Long id);

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

    @Transactional(readOnly = true)
    List<BoardGameMechanicDto> getAllMechanics();

    @Transactional(readOnly = true)
    BoardGameMechanicDto getMechanicById(Long id);

    @Transactional
    BoardGameMechanicDto addMechanic(CreateAndUpdateBoardGameMechanicDto createAndUpdateBoardGameMechanicDto);

    @Transactional
    BoardGameMechanicDto updateMechanic(Long id, CreateAndUpdateBoardGameMechanicDto createAndUpdateBoardGameMechanicDto);

    @Transactional
    void deleteMechanic(Long id);
}
