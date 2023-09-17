package ua.rivnegray.boardgames_shop.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.rivnegray.boardgames_shop.config.custom_configuration_properties.ImageProperties;
import ua.rivnegray.boardgames_shop.config.custom_configuration_properties.PaginationProperties;
import ua.rivnegray.boardgames_shop.mapper.BoardGameMapper;
import ua.rivnegray.boardgames_shop.repository.BoardGameGenreRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameMechanicRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;
import ua.rivnegray.boardgames_shop.repository.ProductImageRepository;

class BoardGameServiceImplTest {
    private BoardGameRepository boardGameRepository;
    private BoardGameMapper boardGameMapper;
    private BoardGameGenreRepository boardGameGenreRepository;
    private BoardGameMechanicRepository boardGameMechanicRepository;
    private EntityManager entityManager;
    private ProductImageRepository productImageRepository;
    private ImageProperties imageProperties;
    private PaginationProperties paginationProperties;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllBoardGames() {
    }

    @Test
    void addBoardGame() {
    }

    @Test
    void getBoardGameById() {
    }

    @Test
    void updateBoardGame() {
    }

    @Test
    void deleteBoardGame() {
    }

    @Test
    void getAllArchivedBoardGames() {
    }

    @Test
    void archiveBoardGame() {
    }

    @Test
    void unarchiveBoardGame() {
    }

    @Test
    void uploadAndAddImage() {
    }

    @Test
    void extractImageExtensionFromFilename() {
    }

    @Test
    void getBoardGameImage() {
    }

    @Test
    void getFilenameMediaType() {
    }
}
