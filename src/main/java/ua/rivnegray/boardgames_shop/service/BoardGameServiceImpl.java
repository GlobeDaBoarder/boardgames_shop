package ua.rivnegray.boardgames_shop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.FilterBoardGamesRequestDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameSummaryDto;
import ua.rivnegray.boardgames_shop.exceptions.badRequestExceptions.FilterRequestDeserializationException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.BoardGameGenreMapper;
import ua.rivnegray.boardgames_shop.mapper.BoardGameMapper;
import ua.rivnegray.boardgames_shop.mapper.BoardGameMechanicMapper;
import ua.rivnegray.boardgames_shop.model.BoardGame;
import ua.rivnegray.boardgames_shop.model.SortType;
import ua.rivnegray.boardgames_shop.repository.BoardGameGenreRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameMechanicRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;
import ua.rivnegray.boardgames_shop.repository.specifications.BoardGameSpecification;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardGameServiceImpl implements BoardGameService {
    private static final int PAGE_SIZE = 10;
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
    public List<BoardGameSummaryDto> getAllBoardGames(String search, String filterDTOEncoded, SortType sort, Integer page) {
        if (search != null && !search.isBlank()) {
            return searchBoardgames(search);
        }
        return this.boardGameRepository.findAll(
                        filterDTOEncoded != null?
                                getFilterSpecificationFromFilterDto(convertFilterStringDtoToFilterDto(filterDTOEncoded)):
                                Specification.where(null),
                        PageRequest.of(page, PAGE_SIZE,
                                sort != null?Sort.by(sort.getDirection(), sort.getProperty()):Sort.unsorted())
                ).stream()
                .map(boardGame -> this.boardGameMapper.boardGameToBoardGameSummaryDto(boardGame))
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

    @Override
    public List<BoardGameSummaryDto> getAllArchivedBoardGames() {
        return this.boardGameRepository.findAllByIsRemovedIsTrue().stream()
                .map(boardGame -> this.boardGameMapper.boardGameToBoardGameSummaryDto(boardGame))
                .collect(Collectors.toList());
    }

    @Override
    public BoardGameDto archiveBoardGame(Long id) {
        BoardGame boardGame = fetchBoardGameById(id);
        boardGame.setIsRemoved(true);
        return this.boardGameMapper.boardGameToBoardGameDto(boardGame);
    }

    @Override
    public BoardGameDto unarchiveBoardGame(Long id) {
        BoardGame boardGame = fetchBoardGameById(id);
        boardGame.setIsRemoved(false);
        return this.boardGameMapper.boardGameToBoardGameDto(boardGame);
    }

    private BoardGame fetchBoardGameById(Long id) {
        return this.boardGameRepository.findById(id)
                .orElseThrow(() -> new BoardGameIdNotFoundException(id));
    }

    private Specification<BoardGame> getFilterSpecificationFromFilterDto(FilterBoardGamesRequestDto filterBoardGamesRequestDto) {
        return Specification.allOf(
                BoardGameSpecification.hasManufacturers(filterBoardGamesRequestDto.manufacturers()),
                BoardGameSpecification.hasPriceInRange(filterBoardGamesRequestDto.minProductPrice(), filterBoardGamesRequestDto.maxProductPrice()),
                BoardGameSpecification.hasBoardGameGenres(filterBoardGamesRequestDto.boardGameGenres()),
                BoardGameSpecification.hasBoardGameMechanics(filterBoardGamesRequestDto.boardGameMechanics()),
                BoardGameSpecification.hasMinAges(filterBoardGamesRequestDto.minAges()),
                BoardGameSpecification.hasPlayersInRange(filterBoardGamesRequestDto.playerCounts()),
                BoardGameSpecification.hasGameDurationInRange(filterBoardGamesRequestDto.minGameDuration(), filterBoardGamesRequestDto.maxGameDuration()),
                BoardGameSpecification.hasLanguage(filterBoardGamesRequestDto.boardGameLanguages()),
                //this is necessary to not show archived boardgames in the search results
                BoardGameSpecification.hasIsRemovedFalse()
        );
    }

    private List<BoardGameSummaryDto> searchBoardgames(String searchValue) {
        List<BoardGame> titleMatches = boardGameRepository.findAllByProductNameContainingIgnoreCase(searchValue);

        List<Long> idsToExclude = titleMatches.stream()
                .map(BoardGame::getId)
                .toList();

        List<BoardGame> descriptionMatches;

        if (idsToExclude.isEmpty()){
            descriptionMatches = boardGameRepository.findAllByProductDescriptionContainingIgnoreCase(searchValue);
        } else {
            descriptionMatches = boardGameRepository.findAllByProductDescriptionContainingIgnoreCaseAndIdNotIn(searchValue, idsToExclude);
        }

        List<BoardGame> combinedResults = new ArrayList<>();
        combinedResults.addAll(titleMatches);
        combinedResults.addAll(descriptionMatches);

        return combinedResults.stream()
                .map(boardGame -> this.boardGameMapper.boardGameToBoardGameSummaryDto(boardGame))
                .collect(Collectors.toList());
    }

    private FilterBoardGamesRequestDto convertFilterStringDtoToFilterDto(String filterDTOEncoded) {
        try {
            String filterDTODecoded = URLDecoder.decode(filterDTOEncoded, StandardCharsets.UTF_8);
            return new ObjectMapper().readValue(filterDTODecoded, FilterBoardGamesRequestDto.class);
        } catch (JsonProcessingException e) {
            throw new FilterRequestDeserializationException(e);
        }
    }
}
