package ua.rivnegray.boardgames_shop.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameGenreDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameMechanicDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameGenreDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameMechanicDto;
import ua.rivnegray.boardgames_shop.exceptions.BoardGameGenreIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.BoardGameIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.BoardGameMechanicIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.BoardGameGenreMapper;
import ua.rivnegray.boardgames_shop.mapper.BoardGameMapper;
import ua.rivnegray.boardgames_shop.mapper.BoardGameMechanicMapper;
import ua.rivnegray.boardgames_shop.model.BoardGame;
import ua.rivnegray.boardgames_shop.model.BoardGameGenre;
import ua.rivnegray.boardgames_shop.model.BoardGameMechanic;
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

    private BoardGame fetchBoardGameById(Long id) {
        return this.boardGameRepository.findById(id)
                .orElseThrow(() -> new BoardGameIdNotFoundException(id));
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
        boardGame = this.boardGameRepository.save(boardGame);
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
        boardGame = this.boardGameRepository.save(boardGame);
        entityManager.flush();
        entityManager.refresh(boardGame); // entity now has the @UpdateTimestamp field updated
        return this.boardGameMapper.boardGameToBoardGameDto(boardGame);
    }

    @Override
    public void deleteBoardGame(Long id) {
        this.boardGameRepository.deleteById(id);
    }

    @Override
    public List<BoardGameGenreDto> getAllGenres() {
        return this.boardGameGenreRepository.findAll().stream()
                .map(boardGameGenre -> this.boardGameGenreMapper.boardGameGenreToBoardGameGenreDto(boardGameGenre))
                .collect(Collectors.toList());
    }

    @Override
    public BoardGameGenreDto getGenreById(Long id) {
        return this.boardGameGenreMapper.boardGameGenreToBoardGameGenreDto(this.boardGameGenreRepository.findById(id)
                .orElseThrow(() -> new BoardGameGenreIdNotFoundException(id)));
    }

    @Override
    public BoardGameGenreDto addGenre(CreateAndUpdateBoardGameGenreDto createAndUpdateBoardGameGenreDto) {
        BoardGameGenre boardGameGenre = this.boardGameGenreMapper
                .createBoardGameGenreDtoToBoardGameGenre(createAndUpdateBoardGameGenreDto);
        boardGameGenre = this.boardGameGenreRepository.save(boardGameGenre);
        return this.boardGameGenreMapper.boardGameGenreToBoardGameGenreDto(boardGameGenre);
    }

    @Override
    public BoardGameGenreDto updateGenre(Long id, CreateAndUpdateBoardGameGenreDto createAndUpdateBoardGameGenreDto) {
        BoardGameGenre boardGameGenre = this.boardGameGenreRepository.findById(id)
                .orElseThrow(() -> new BoardGameGenreIdNotFoundException(id));
        boardGameGenre = this.boardGameGenreMapper.updateBoardGameGenreFromDto(createAndUpdateBoardGameGenreDto, boardGameGenre);
        boardGameGenre = this.boardGameGenreRepository.save(boardGameGenre);
        return this.boardGameGenreMapper.boardGameGenreToBoardGameGenreDto(boardGameGenre);
    }

    @Override
    public void deleteGenre(Long id) {
        this.boardGameGenreRepository.deleteById(id);
    }

    @Override
    public List<BoardGameMechanicDto> getAllMechanics() {
        return this.boardGameMechanicRepository.findAll().stream()
                .map(boardGameMechanic -> this.boardGameMechanicMapper
                        .boardGameMechanicToBoardGameMechanicDto(boardGameMechanic))
                .collect(Collectors.toList());
    }

    @Override
    public BoardGameMechanicDto getMechanicById(Long id) {
        return this.boardGameMechanicMapper.boardGameMechanicToBoardGameMechanicDto(this.boardGameMechanicRepository
                .findById(id).orElseThrow(() -> new BoardGameMechanicIdNotFoundException(id)));
    }

    @Override
    public BoardGameMechanicDto addMechanic(CreateAndUpdateBoardGameMechanicDto createAndUpdateBoardGameMechanicDto) {
        BoardGameMechanic boardGameMechanic = this.boardGameMechanicMapper
                .createBoardGameMechanicDtoToBoardGameMechanic(createAndUpdateBoardGameMechanicDto);
        boardGameMechanic = this.boardGameMechanicRepository.save(boardGameMechanic);
        return this.boardGameMechanicMapper.boardGameMechanicToBoardGameMechanicDto(boardGameMechanic);
    }

    @Override
    public BoardGameMechanicDto updateMechanic(Long id, CreateAndUpdateBoardGameMechanicDto createAndUpdateBoardGameMechanicDto) {
        BoardGameMechanic boardGameMechanic = this.boardGameMechanicRepository.findById(id)
                .orElseThrow(() -> new BoardGameMechanicIdNotFoundException(id));
        boardGameMechanic = this.boardGameMechanicMapper.updateBoardGameMechanicFromDto(createAndUpdateBoardGameMechanicDto,
                boardGameMechanic);
        boardGameMechanic = this.boardGameMechanicRepository.save(boardGameMechanic);
        return this.boardGameMechanicMapper.boardGameMechanicToBoardGameMechanicDto(boardGameMechanic);
    }

    @Override
    public void deleteMechanic(Long id) {
        this.boardGameMechanicRepository.deleteById(id);
    }

}
