package generated.board_game.api;

import jakarta.annotation.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameGenreDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAndUpdateBoardGameMechanicDto;
import ua.rivnegray.boardgames_shop.DTO.response.*;
import ua.rivnegray.boardgames_shop.model.SortType;

import java.util.List;
import java.util.Optional;

/**
 * A delegate to be called by the {@link BoardgamesApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
public interface BoardgamesApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /boardgames : Add a new boardgame
     *
     * @param createAndUpdateBoardGameDto Boardgame to add (required)
     * @return Created (status code 201)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     * @see BoardgamesApi#addBoardGame
     */
    default ResponseEntity<BoardGameDto> addBoardGame(CreateAndUpdateBoardGameDto createAndUpdateBoardGameDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /boardgames/genres : Add a new genre
     *
     * @param createAndUpdateBoardGameGenreDto Genre to add (required)
     * @return Created (status code 201)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     * @see BoardgamesApi#addGenre
     */
    default ResponseEntity<BoardGameGenreDto> addGenre(CreateAndUpdateBoardGameGenreDto createAndUpdateBoardGameGenreDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /boardgames/mechanics : Add a new mechanic
     *
     * @param createAndUpdateBoardGameMechanicDto Mechanic to add (required)
     * @return Created (status code 201)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     * @see BoardgamesApi#addMechanic
     */
    default ResponseEntity<BoardGameMechanicDto> addMechanic(CreateAndUpdateBoardGameMechanicDto createAndUpdateBoardGameMechanicDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /boardgames/archive/{id} : archive board game
     *
     * @param id ID of the board game to archive (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     *         or Unauthorized (status code 401)
     * @see BoardgamesApi#archiveBoardGame
     */
    default ResponseEntity<BoardGameDto> archiveBoardGame(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /boardgames/{id} : Delete a boardgame
     *
     * @param id ID of the boardgame to delete (required)
     * @return No Content (status code 204)
     *         or Not Found (status code 404)
     *         or Unauthorized (status code 401)
     * @see BoardgamesApi#deleteBoardGame
     */
    default ResponseEntity<Void> deleteBoardGame(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /boardgames/genres/{id} : Delete a genre
     *
     * @param id ID of the genre to delete (required)
     * @return No Content (status code 204)
     *         or Not Found (status code 404)
     *         or Unauthorized (status code 401)
     * @see BoardgamesApi#deleteGenre
     */
    default ResponseEntity<Void> deleteGenre(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /boardgames/mechanics/{id} : Delete a mechanic
     *
     * @param id ID of the mechanic to delete (required)
     * @return No Content (status code 204)
     *         or Not Found (status code 404)
     *         or Unauthorized (status code 401)
     * @see BoardgamesApi#deleteMechanic
     */
    default ResponseEntity<Void> deleteMechanic(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /boardgames/archived/ : get all archived boardgames
     *
     * @return OK (status code 200)
     * @see BoardgamesApi#getAllArchivedBoardGames
     */
    default ResponseEntity<List<BoardGameSummaryDto>> getAllArchivedBoardGames() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"emptyValue\" : true }, { \"emptyValue\" : true } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /boardgames : Get all boardgames
     *
     * @param search search in product name and description for some key words (optional)
     * @param filter  For Filtering you have to URL encode a DTO in Json format. here&#39;s an example of possible DTO: { \&quot;manufacturers\&quot;:[\&quot;Kosmos\&quot;], \&quot;minProductPrice\&quot;:90, \&quot;maxProductPrice\&quot;:110, \&quot;boardGameTypes\&quot;: [\&quot;Стратегії\&quot;], \&quot;boardGameGenres\&quot;:[\&quot;Сімейні\&quot;,\&quot;Економіка\&quot;], \&quot;boardGameMechanics\&quot;:[\&quot;Конструювання колоди\&quot;,\&quot;Кооперативна гра\&quot;], \&quot;ageIntervals\&quot;:[\&quot;10-13\&quot;,\&quot;8-9\&quot;],\&quot;playerCounts\&quot;:[\&quot;2\&quot;,\&quot;3\&quot;,\&quot;4\&quot;,\&quot;5\&quot;], \&quot;minGameDuration\&quot;:30,\&quot;maxGameDuration\&quot;:130, \&quot;boardGameLanguages\&quot;:[\&quot;Англійська\&quot;] }  (optional)
     * @param sort sort by: price (asc, desc), name (asc, desc), newest (optional)
     * @param page current page number (optional, default to 0)
     * @return OK (status code 200)
     * @see BoardgamesApi#getAllBoardGames
     */
    default ResponseEntity<CatalogResponseDto> getAllBoardGames(String search,
        String filter,
        SortType sort,
        Integer page) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /boardgames/genres : Get all genres
     *
     * @return OK (status code 200)
     * @see BoardgamesApi#getAllGenres
     */
    default ResponseEntity<List<BoardGameGenreDto>> getAllGenres() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"emptyValue\" : true }, { \"emptyValue\" : true } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /boardgames/mechanics : Get all mechanics
     *
     * @return OK (status code 200)
     * @see BoardgamesApi#getAllMechanics
     */
    default ResponseEntity<List<BoardGameMechanicDto>> getAllMechanics() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"emptyValue\" : true }, { \"emptyValue\" : true } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /boardgames/{id} : Get a boardgame by id
     *
     * @param id ID of the boardgame (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     * @see BoardgamesApi#getBoardGameById
     */
    default ResponseEntity<BoardGameDto> getBoardGameById(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /boardgames/images/{filename} : Retrieve a specific image for a board game
     *
     * @param filename The filename of the image to retrieve (e.g., \&quot;1.png\&quot;) (required)
     * @return Image retrieved successfully (status code 200)
     *         or Bad Request (status code 400)
     *         or Boardgame or Image not found (status code 404)
     * @see BoardgamesApi#getBoardGameImage
     */
    default ResponseEntity<org.springframework.core.io.Resource> getBoardGameImage(String filename) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /boardgames/filteringData : Get all filtering data
     *
     * @return OK (status code 200)
     * @see BoardgamesApi#getFilteringData
     */
    default ResponseEntity<FilterDataDto> getFilteringData() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /boardgames/gameDurationBounds : Get min and max game durations from all games
     *
     * @return OK (status code 200)
     * @deprecated
     * @see BoardgamesApi#getGameDurationBounds
     */
    @Deprecated
    default ResponseEntity<MinMaxDto> getGameDurationBounds() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /boardgames/genres/{id} : Get a genre by id
     *
     * @param id ID of the genre (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     * @see BoardgamesApi#getGenreById
     */
    default ResponseEntity<BoardGameGenreDto> getGenreById(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /boardgames/mechanics/{id} : Get a mechanic by id
     *
     * @param id ID of the mechanic (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     * @see BoardgamesApi#getMechanicById
     */
    default ResponseEntity<BoardGameMechanicDto> getMechanicById(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /boardgames/newest-five : Get five newest boardgames
     * Get five newest boardgames
     *
     * @return OK (status code 200)
     * @see BoardgamesApi#getNewestFiveBoardGames
     */
    default ResponseEntity<List<BoardGameSummaryDto>> getNewestFiveBoardGames() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"emptyValue\" : true }, { \"emptyValue\" : true } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /boardgames/priceBounds : Get min and max price from all games
     *
     * @return OK (status code 200)
     * @deprecated
     * @see BoardgamesApi#getPriceBounds
     */
    @Deprecated
    default ResponseEntity<MinMaxDto> getPriceBounds() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /boardgames/unarchive/{id} : unarchive board game
     *
     * @param id ID of the board game to unarchive (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     *         or Unauthorized (status code 401)
     * @see BoardgamesApi#unarchiveBoardGame
     */
    default ResponseEntity<BoardGameDto> unarchiveBoardGame(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /boardgames/{id} : Update a boardgame
     *
     * @param id ID of the boardgame to update (required)
     * @param createAndUpdateBoardGameDto Boardgame to update (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     *         or Unauthorized (status code 401)
     * @see BoardgamesApi#updateBoardGame
     */
    default ResponseEntity<BoardGameDto> updateBoardGame(Long id,
        CreateAndUpdateBoardGameDto createAndUpdateBoardGameDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /boardgames/genres/{id} : Update a genre
     *
     * @param id ID of the genre to update (required)
     * @param createAndUpdateBoardGameGenreDto Genre to update (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     *         or Unauthorized (status code 401)
     * @see BoardgamesApi#updateGenre
     */
    default ResponseEntity<BoardGameGenreDto> updateGenre(Long id,
        CreateAndUpdateBoardGameGenreDto createAndUpdateBoardGameGenreDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /boardgames/mechanics/{id} : Update a mechanic
     *
     * @param id ID of the mechanic to update (required)
     * @param createAndUpdateBoardGameMechanicDto Mechanic to update (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     *         or Unauthorized (status code 401)
     * @see BoardgamesApi#updateMechanic
     */
    default ResponseEntity<BoardGameMechanicDto> updateMechanic(Long id,
        CreateAndUpdateBoardGameMechanicDto createAndUpdateBoardGameMechanicDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /boardgames/{id}/image : Upload an image for a specific boardgame
     *
     * @param id The ID of the boardgame to which the image will be associated (required)
     * @param file  (optional)
     * @return Image uploaded and associated successfully (status code 201)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Boardgame not found (status code 404)
     * @see BoardgamesApi#uploadAndAddImage
     */
    default ResponseEntity<BoardGameDto> uploadAndAddImage(Long id,
        MultipartFile file) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
