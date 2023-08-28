package ua.rivnegray.boardgames_shop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.rivnegray.boardgames_shop.DTO.request.FilterBoardGamesRequestDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameSummaryDto;
import ua.rivnegray.boardgames_shop.config.custom_configuration_properties.ImageProperties;
import ua.rivnegray.boardgames_shop.config.custom_configuration_properties.PaginationProperties;
import ua.rivnegray.boardgames_shop.exceptions.badRequestExceptions.FilterRequestDeserializationException;
import ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions.ImageFileSaveException;
import ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions.UnsupportedFileExtensionException;
import ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions.UnsupportedFilenameException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.BoardGameGenreMapper;
import ua.rivnegray.boardgames_shop.mapper.BoardGameMapper;
import ua.rivnegray.boardgames_shop.mapper.BoardGameMechanicMapper;
import ua.rivnegray.boardgames_shop.model.BoardGame;
import ua.rivnegray.boardgames_shop.model.ProductImage;
import ua.rivnegray.boardgames_shop.model.SortType;
import ua.rivnegray.boardgames_shop.repository.BoardGameGenreRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameMechanicRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;
import ua.rivnegray.boardgames_shop.repository.ProductImageRepository;
import ua.rivnegray.boardgames_shop.repository.specifications.BoardGameSpecification;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardGameServiceImpl implements BoardGameService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BoardGameServiceImpl.class);

    BoardGameRepository boardGameRepository;
    BoardGameMapper boardGameMapper;
    BoardGameGenreRepository boardGameGenreRepository;
    BoardGameGenreMapper boardGameGenreMapper;
    BoardGameMechanicRepository boardGameMechanicRepository;
    BoardGameMechanicMapper boardGameMechanicMapper;
    EntityManager  entityManager;
    ProductImageRepository productImageRepository;

    ImageProperties imageProperties;
    PaginationProperties paginationProperties;

    @Autowired
    public BoardGameServiceImpl(BoardGameRepository boardGameRepository, BoardGameMapper boardGameMapper,
                                BoardGameGenreRepository boardGameGenreRepository,
                                BoardGameMechanicRepository boardGameMechanicRepository,
                                EntityManager entityManager,
                                BoardGameGenreMapper boardGameGenreMapper,
                                BoardGameMechanicMapper boardGameMechanicMapper,
                                ProductImageRepository productImageRepository,
                                ImageProperties imageProperties,
                                PaginationProperties paginationProperties){
        this.boardGameRepository = boardGameRepository;
        this.boardGameMapper = boardGameMapper;
        this.boardGameGenreRepository = boardGameGenreRepository;
        this.boardGameMechanicRepository = boardGameMechanicRepository;
        this.entityManager = entityManager;
        this.boardGameGenreMapper = boardGameGenreMapper;
        this.boardGameMechanicMapper = boardGameMechanicMapper;
        this.productImageRepository = productImageRepository;
        this.imageProperties = imageProperties;
        this.paginationProperties = paginationProperties;
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
                        PageRequest.of(page, paginationProperties.getPageSize(),
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

    /**
     *
     * @param id id of board game to add image to
     * @param imageFile image file to add
     * @throws ImageFileSaveException if image file saving failed
     * @return BoardGameDto with added image
     */
    @Override
    public BoardGameDto uploadAndAddImage(Long id, MultipartFile imageFile) {
        try {
            ProductImage productImage = this.productImageRepository.save(new ProductImage());

            Path filePath = Path.of(imageProperties.getStoragePath() + productImage.getId().toString()
                    + extractImageExtensionFromFilename(imageFile.getOriginalFilename()));

            LOGGER.debug("saving image at: " + filePath);

            BoardGame boardgame = this.fetchBoardGameById(id);
            productImage.setImagePath(filePath.toString());
            productImage.setProduct(boardgame);
            productImage.setOriginalFileName(imageFile.getOriginalFilename());
            productImage.setImageURL(imageProperties.getEndpointBaseUrl() + filePath.getFileName());

            boardgame.getProductImages().add(productImage);
            this.boardGameRepository.save(boardgame);

            byte[] bytes = imageFile.getBytes();
            Files.write(filePath, bytes);

            return this.boardGameMapper.boardGameToBoardGameDto(boardgame);
        } catch (IOException e) {
            throw new ImageFileSaveException(e);
        }
    }

    /**
     * <h2>Extracts image extension from filename</h2>
     * @param filename filename to extract extension from
     * @throws UnsupportedFilenameException if filename is null or blank
     * @throws UnsupportedFileExtensionException if filename has unsupported extension
     * @return image extension
     */
    @Override
    public  String extractImageExtensionFromFilename(@Nullable String filename){
        if (filename == null || filename.isBlank()){
            throw new UnsupportedFilenameException(filename);
        }

        String extension =  filename.substring(filename.lastIndexOf("."));
        if (!imageProperties.getSupportedImageFileExtensions().contains(extension)) {
            throw new UnsupportedFileExtensionException(extension);
        }

        return extension;
    }

    @Override
    public Resource getBoardGameImage(String filename) {
        return new FileSystemResource(imageProperties.getStoragePath() + filename);
    }

    @Override
    public MediaType getFilenameMediaType(String filename) {
        String extension = extractImageExtensionFromFilename(filename);
        if (extension.equals(".jpg") || extension.equals(".jpeg")){
            return MediaType.IMAGE_JPEG;
        } else if (extension.equals(".png")){
            return MediaType.IMAGE_PNG;
        } else {
            throw new UnsupportedFileExtensionException(extension);
        }
    }
}
