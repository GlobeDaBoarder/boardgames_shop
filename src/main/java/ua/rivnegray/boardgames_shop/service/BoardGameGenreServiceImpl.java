package ua.rivnegray.boardgames_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameGenreDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameGenreDto;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameGenreIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.BoardGameGenreMapper;
import ua.rivnegray.boardgames_shop.model.BoardGameGenre;
import ua.rivnegray.boardgames_shop.repository.BoardGameGenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardGameGenreServiceImpl implements BoardGameGenreService {
    private final BoardGameGenreRepository boardGameGenreRepository;
    private final BoardGameGenreMapper boardGameGenreMapper;

    @Autowired
    BoardGameGenreServiceImpl(BoardGameGenreRepository boardGameGenreRepository,
                                     BoardGameGenreMapper boardGameGenreMapper) {
        this.boardGameGenreRepository = boardGameGenreRepository;
        this.boardGameGenreMapper = boardGameGenreMapper;
    }

    @Override
    public List<BoardGameGenreDto> getAllGenres() {
        return this.boardGameGenreRepository.findAll().stream()
                .map(this.boardGameGenreMapper::boardGameGenreToBoardGameGenreDto)
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
        this.boardGameGenreRepository.save(boardGameGenre);
        return this.boardGameGenreMapper.boardGameGenreToBoardGameGenreDto(boardGameGenre);
    }

    @Override
    public BoardGameGenreDto updateGenre(Long id, CreateAndUpdateBoardGameGenreDto createAndUpdateBoardGameGenreDto) {
        BoardGameGenre boardGameGenre = this.boardGameGenreRepository.findById(id)
                .orElseThrow(() -> new BoardGameGenreIdNotFoundException(id));
        this.boardGameGenreMapper.updateBoardGameGenreFromDto(createAndUpdateBoardGameGenreDto, boardGameGenre);
        this.boardGameGenreRepository.save(boardGameGenre);
        return this.boardGameGenreMapper.boardGameGenreToBoardGameGenreDto(boardGameGenre);
    }

    @Override
    public void deleteGenre(Long id) {
        this.boardGameGenreRepository.deleteById(id);
    }
}
