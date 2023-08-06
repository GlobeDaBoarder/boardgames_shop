package ua.rivnegray.boardgames_shop.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.BoardGameGenreMapper;
import ua.rivnegray.boardgames_shop.mapper.BoardGameMapper;
import ua.rivnegray.boardgames_shop.mapper.BoardGameMechanicMapper;
import ua.rivnegray.boardgames_shop.model.BoardGame;
import ua.rivnegray.boardgames_shop.repository.BoardGameGenreRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameMechanicRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardGameServiceImpl implements BoardGameService {
    BoardGameRepository boardGameRepository;
    BoardGameMapper boardGameMapper;

    BoardGameGenreRepository boardGameGenreRepository;

    BoardGameGenreMapper boardGameGenreMapper;

    BoardGameMechanicRepository boardGameMechanicRepository;

    BoardGameMechanicMapper boardGameMechanicMapper;

    EntityManager  entityManager;

    @Autowired
    public BoardGameServiceImpl(BoardGameRepository boardGameRepository, BoardGameMapper boardGameMapper,
                                BoardGameGenreRepository boardGameGenreRepository,
                                BoardGameMechanicRepository boardGameMechanicRepository,
                                EntityManager entityManager,
                                BoardGameGenreMapper boardGameGenreMapper,
                                BoardGameMechanicMapper boardGameMechanicMapper) {
        this.boardGameRepository = boardGameRepository;
        this.boardGameMapper = boardGameMapper;
        this.boardGameGenreRepository = boardGameGenreRepository;
        this.boardGameMechanicRepository = boardGameMechanicRepository;
        this.entityManager = entityManager;
        this.boardGameGenreMapper = boardGameGenreMapper;
        this.boardGameMechanicMapper = boardGameMechanicMapper;
    }

    @Override
    public List<BoardGameDto> getAllBoardGames() {
        return this.boardGameRepository.findAll().stream()
                .map(boardGame -> this.boardGameMapper.boardGameToBoardGameDto(boardGame))
                .collect(Collectors.toList());
    }

    @Override
    public BoardGameDto addBoardGame(CreateAndUpdateBoardGameDto createBoardGameDto) {
        BoardGame boardGame = this.boardGameMapper.createBoardGameDtoToBoardGame(createBoardGameDto,
                this.boardGameGenreRepository, this.boardGameMechanicRepository);
        this.boardGameRepository.save(boardGame);
        entityManager.flush();
        entityManager.refresh(boardGame); // entity now has the @CreationTimestamp field set
        return this.boardGameMapper.boardGameToBoardGameDto(boardGame);
    }

    @Override
    public BoardGameDto getBoardGameById(Long id) {
        return this.boardGameMapper.boardGameToBoardGameDto(fetchBoardGameById(id));
    }

    @Override
    public BoardGameDto updateBoardGame(Long id, CreateAndUpdateBoardGameDto updateBoardGameDto) {
        BoardGame boardGame = fetchBoardGameById(id);
        this.boardGameMapper.updateBoardGameFromDto(updateBoardGameDto, boardGame, this.boardGameGenreRepository,
                this.boardGameMechanicRepository);
        this.boardGameRepository.save(boardGame);
        entityManager.flush();
        entityManager.refresh(boardGame); // entity now has the @UpdateTimestamp field updated
        return this.boardGameMapper.boardGameToBoardGameDto(boardGame);
    }

    @Override
    public void deleteBoardGame(Long id) {
        this.boardGameRepository.deleteById(id);
    }

    private BoardGame fetchBoardGameById(Long id) {
        return this.boardGameRepository.findById(id)
                .orElseThrow(() -> new BoardGameIdNotFoundException(id));
    }
}
