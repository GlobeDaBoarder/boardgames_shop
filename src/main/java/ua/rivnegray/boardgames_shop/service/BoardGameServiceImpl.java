package ua.rivnegray.boardgames_shop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.rivnegray.boardgames_shop.DTO.request.FilterBoardGamesRequestDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.response.BoardGameSummaryDto;
import ua.rivnegray.boardgames_shop.DTO.response.CatalogResponseDto;
import ua.rivnegray.boardgames_shop.DTO.response.MinMaxDto;
import ua.rivnegray.boardgames_shop.config.custom_configuration_properties.ImageProperties;
import ua.rivnegray.boardgames_shop.config.custom_configuration_properties.PaginationProperties;
import ua.rivnegray.boardgames_shop.exceptions.badRequestExceptions.FilterRequestDeserializationException;
import ua.rivnegray.boardgames_shop.exceptions.conflictExceptions.ImageAlreadyExistsException;
import ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions.ImageFileSaveException;
import ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions.UnsupportedFileExtensionException;
import ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions.UnsupportedFilenameException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.BoardGameMapper;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardGameServiceImpl implements BoardGameService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BoardGameServiceImpl.class);

    private final BoardGameRepository boardGameRepository;
    private final BoardGameMapper boardGameMapper;
    private final BoardGameGenreRepository boardGameGenreRepository;
    private final BoardGameMechanicRepository boardGameMechanicRepository;
    private final EntityManager  entityManager;
    private final ProductImageRepository productImageRepository;
    private final ImageProperties imageProperties;
    private final PaginationProperties paginationProperties;

    @Autowired
    BoardGameServiceImpl(BoardGameRepository boardGameRepository, BoardGameMapper boardGameMapper,
                                BoardGameGenreRepository boardGameGenreRepository,
                                BoardGameMechanicRepository boardGameMechanicRepository,
                                EntityManager entityManager,
                                ProductImageRepository productImageRepository,
                                ImageProperties imageProperties,
                                PaginationProperties paginationProperties){
        this.boardGameRepository = boardGameRepository;
        this.boardGameMapper = boardGameMapper;
        this.boardGameGenreRepository = boardGameGenreRepository;
        this.boardGameMechanicRepository = boardGameMechanicRepository;
        this.entityManager = entityManager;
        this.productImageRepository = productImageRepository;
        this.imageProperties = imageProperties;
        this.paginationProperties = paginationProperties;
    }

    @Override
    public CatalogResponseDto getAllBoardGames(String search, String filterDTOEncoded, SortType sort, Integer pageNumber) {
        if (search != null && !search.isBlank()) {
            return searchBoardGames(search, pageNumber);
        }
        Page<BoardGame> page = this.boardGameRepository.findAll(
                        filterDTOEncoded != null?
                                getFilterSpecificationFromFilterDto(convertFilterStringDtoToFilterDto(filterDTOEncoded)):
                                Specification.where(null),
                        PageRequest.of(pageNumber, paginationProperties.getPageSize(),
                                sort != null?Sort.by(sort.getDirection(), sort.getProperty()):Sort.unsorted())
                );

        return new CatalogResponseDto(
                page.getContent().stream()
                        .map(this.boardGameMapper::boardGameToBoardGameSummaryDto)
                        .collect(Collectors.toList()),
                page.getTotalPages()
        );

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
                this.boardGameMechanicRepository, this.productImageRepository);
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
                .map(this.boardGameMapper::boardGameToBoardGameSummaryDto)
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
                // check for isRemoved false to only display non-archived board games
                BoardGameSpecification.hasIsRemovedFalse()
        );
    }

    private CatalogResponseDto searchBoardGames(String searchValue, Integer pageNumber) {
        Page<BoardGame> page = this.boardGameRepository
                .findAllByProductNameAndProductDescriptionContainingIgnoreCaseAndIsRemovedIsFalse(
                        searchValue,
                        PageRequest.of(pageNumber, paginationProperties.getPageSize()));

        return new CatalogResponseDto(
                page.getContent().stream()
                        .map(this.boardGameMapper::boardGameToBoardGameSummaryDto)
                        .collect(Collectors.toList()),
                page.getTotalPages()
        );
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
            //check if image exists in db
            checkImageExistence(imageFile);

            // save image entity to db
            ProductImage productImage = this.productImageRepository.save(new ProductImage());
            Path filePath = Path.of(imageProperties.getStoragePath() + productImage.getId().toString()
                    + extractImageExtensionFromFilename(imageFile.getOriginalFilename()));

            LOGGER.debug("saving image at: " + filePath);

            BoardGame boardgame = this.fetchBoardGameById(id);
            productImage.setImagePath(filePath.toString());
            productImage.setProduct(boardgame);
            productImage.setOriginalFileName(imageFile.getOriginalFilename());
            productImage.setImageURL(imageProperties.getServerBaseUrl() + imageProperties.getEndpointBaseUrl() + filePath.getFileName());
            productImage.setImageHash(DigestUtils.md5DigestAsHex(imageFile.getBytes()));

            boardgame.getProductImages().add(productImage);
            this.boardGameRepository.save(boardgame);

            // save image file to filesystem
            byte[] bytes = imageFile.getBytes();
            Files.write(filePath, bytes);

            return this.boardGameMapper.boardGameToBoardGameDto(boardgame);
        } catch (IOException e) {
            throw new ImageFileSaveException(imageFile.getOriginalFilename(), e);
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


    /**
     * Checks if image with same hash already exists in db
     * @param imageFile image file to check
     * @throws ImageAlreadyExistsException if image with same hash already exists in db
     */
    private void checkImageExistence(MultipartFile imageFile) throws IOException {
        String imageHash = DigestUtils.md5DigestAsHex(imageFile.getBytes());
        this.productImageRepository.findByImageHash(imageHash).ifPresent(productImage -> {
            throw new ImageAlreadyExistsException(imageHash);
        });
    }

    @Override
    public MinMaxDto getGameDurationBounds() {
        return this.boardGameRepository.findMinMaxGameDuration();
    }

    @Override
    public MinMaxDto getPriceBounds() {
        return this.boardGameRepository.findMinMaxPrice();
    }
}
