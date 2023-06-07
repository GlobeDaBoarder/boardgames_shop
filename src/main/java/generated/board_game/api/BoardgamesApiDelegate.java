package generated.board_game.api;

import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateBoardGameDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link BoardgamesApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-07T12:17:47.268977149+03:00[Europe/Kiev]")
public interface BoardgamesApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /boardgames : Add a new boardgame
     *
     * @param createBoardGameDto Boardgame to add (required)
     * @return Created (status code 201)
     *         or Bad Request (status code 400)
     * @see BoardgamesApi#addBoardGame
     */
    default ResponseEntity<BoardGameDto> addBoardGame(CreateBoardGameDto createBoardGameDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"productImageURL\" : \"productImageURL\", \"gameDuration\" : \"gameDuration\", \"minPlayers\" : 5, \"gameSet\" : \"gameSet\", \"gameMechanicMechanicNames\" : [ \"gameMechanicMechanicNames\", \"gameMechanicMechanicNames\" ], \"BGGLink\" : \"https://openapi-generator.tech\", \"productName\" : \"productName\", \"dateUpdated\" : \"2000-01-23T04:56:07.000+00:00\", \"dateCreated\" : \"2000-01-23T04:56:07.000+00:00\", \"isRemoved\" : true, \"productCode\" : \"productCode\", \"minAge\" : 5, \"Manufacturer\" : \"Manufacturer\", \"id\" : 0, \"gameGenreGenreNames\" : [ \"gameGenreGenreNames\", \"gameGenreGenreNames\" ], \"productDescription\" : \"productDescription\", \"productPrice\" : 6.0274563, \"productQuantityInStock\" : 1 }";
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
     * @see BoardgamesApi#deleteBoardGame
     */
    default ResponseEntity<Void> deleteBoardGame(Integer id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /boardgames : Get all boardgames
     *
     * @return OK (status code 200)
     * @see BoardgamesApi#getAllBoardGames
     */
    default ResponseEntity<List<BoardGameDto>> getAllBoardGames() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"productImageURL\" : \"productImageURL\", \"gameDuration\" : \"gameDuration\", \"minPlayers\" : 5, \"gameSet\" : \"gameSet\", \"gameMechanicMechanicNames\" : [ \"gameMechanicMechanicNames\", \"gameMechanicMechanicNames\" ], \"BGGLink\" : \"https://openapi-generator.tech\", \"productName\" : \"productName\", \"dateUpdated\" : \"2000-01-23T04:56:07.000+00:00\", \"dateCreated\" : \"2000-01-23T04:56:07.000+00:00\", \"isRemoved\" : true, \"productCode\" : \"productCode\", \"minAge\" : 5, \"Manufacturer\" : \"Manufacturer\", \"id\" : 0, \"gameGenreGenreNames\" : [ \"gameGenreGenreNames\", \"gameGenreGenreNames\" ], \"productDescription\" : \"productDescription\", \"productPrice\" : 6.0274563, \"productQuantityInStock\" : 1 }, { \"productImageURL\" : \"productImageURL\", \"gameDuration\" : \"gameDuration\", \"minPlayers\" : 5, \"gameSet\" : \"gameSet\", \"gameMechanicMechanicNames\" : [ \"gameMechanicMechanicNames\", \"gameMechanicMechanicNames\" ], \"BGGLink\" : \"https://openapi-generator.tech\", \"productName\" : \"productName\", \"dateUpdated\" : \"2000-01-23T04:56:07.000+00:00\", \"dateCreated\" : \"2000-01-23T04:56:07.000+00:00\", \"isRemoved\" : true, \"productCode\" : \"productCode\", \"minAge\" : 5, \"Manufacturer\" : \"Manufacturer\", \"id\" : 0, \"gameGenreGenreNames\" : [ \"gameGenreGenreNames\", \"gameGenreGenreNames\" ], \"productDescription\" : \"productDescription\", \"productPrice\" : 6.0274563, \"productQuantityInStock\" : 1 } ]";
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
    default ResponseEntity<BoardGameDto> getBoardGameById(Integer id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"productImageURL\" : \"productImageURL\", \"gameDuration\" : \"gameDuration\", \"minPlayers\" : 5, \"gameSet\" : \"gameSet\", \"gameMechanicMechanicNames\" : [ \"gameMechanicMechanicNames\", \"gameMechanicMechanicNames\" ], \"BGGLink\" : \"https://openapi-generator.tech\", \"productName\" : \"productName\", \"dateUpdated\" : \"2000-01-23T04:56:07.000+00:00\", \"dateCreated\" : \"2000-01-23T04:56:07.000+00:00\", \"isRemoved\" : true, \"productCode\" : \"productCode\", \"minAge\" : 5, \"Manufacturer\" : \"Manufacturer\", \"id\" : 0, \"gameGenreGenreNames\" : [ \"gameGenreGenreNames\", \"gameGenreGenreNames\" ], \"productDescription\" : \"productDescription\", \"productPrice\" : 6.0274563, \"productQuantityInStock\" : 1 }";
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
     * @param boardGameDto Boardgame to update (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     * @see BoardgamesApi#updateBoardGame
     */
    default ResponseEntity<BoardGameDto> updateBoardGame(Integer id,
        BoardGameDto boardGameDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"productImageURL\" : \"productImageURL\", \"gameDuration\" : \"gameDuration\", \"minPlayers\" : 5, \"gameSet\" : \"gameSet\", \"gameMechanicMechanicNames\" : [ \"gameMechanicMechanicNames\", \"gameMechanicMechanicNames\" ], \"BGGLink\" : \"https://openapi-generator.tech\", \"productName\" : \"productName\", \"dateUpdated\" : \"2000-01-23T04:56:07.000+00:00\", \"dateCreated\" : \"2000-01-23T04:56:07.000+00:00\", \"isRemoved\" : true, \"productCode\" : \"productCode\", \"minAge\" : 5, \"Manufacturer\" : \"Manufacturer\", \"id\" : 0, \"gameGenreGenreNames\" : [ \"gameGenreGenreNames\", \"gameGenreGenreNames\" ], \"productDescription\" : \"productDescription\", \"productPrice\" : 6.0274563, \"productQuantityInStock\" : 1 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
