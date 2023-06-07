/**
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package generated.board_game.api;

import ua.rivnegray.boardgames_shop.DTO.response.BoardGameDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateBoardGameDto;
    import io.swagger.v3.oas.annotations.ExternalDocumentation;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.Parameter;
    import io.swagger.v3.oas.annotations.Parameters;
    import io.swagger.v3.oas.annotations.media.ArraySchema;
    import io.swagger.v3.oas.annotations.media.Content;
    import io.swagger.v3.oas.annotations.media.Schema;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.security.SecurityRequirement;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import io.swagger.v3.oas.annotations.enums.ParameterIn;
    import org.springframework.http.ResponseEntity;
    import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

    import javax.validation.Valid;
    import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.springframework.security.access.prepost.PreAuthorize;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-07T12:17:47.268977149+03:00[Europe/Kiev]")
    @Validated
    @Tag(name = "boardgames", description = "the boardgames API")
    public interface BoardgamesApi {

                default BoardgamesApiDelegate getDelegate() {
                return new BoardgamesApiDelegate() {};
                }

            /**
            * POST /boardgames : Add a new boardgame
            *
                * @param createBoardGameDto Boardgame to add (required)
            * @return Created (status code 201)
                *         or Bad Request (status code 400)
            */
                @Operation(
                operationId = "addBoardGame",
                    summary = "Add a new boardgame",
                responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = BoardGameDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
                }
                )
            @PreAuthorize("hasAuthority('')")
            @RequestMapping(
            method = RequestMethod.POST,
            value = "/boardgames",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<BoardGameDto> addBoardGame(
        @Parameter(name = "CreateBoardGameDto", description = "Boardgame to add", required = true) @Valid @RequestBody CreateBoardGameDto createBoardGameDto
            ) {
            return getDelegate().addBoardGame(createBoardGameDto);
            }


            /**
            * DELETE /boardgames/{id} : Delete a boardgame
            *
                * @param id ID of the boardgame to delete (required)
            * @return No Content (status code 204)
                *         or Not Found (status code 404)
            */
                @Operation(
                operationId = "deleteBoardGame",
                    summary = "Delete a boardgame",
                responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
                }
                )
            @PreAuthorize("hasAuthority('')")
            @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/boardgames/{id}"
            )
        default ResponseEntity<Void> deleteBoardGame(
        @Parameter(name = "id", description = "ID of the boardgame to delete", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
            ) {
            return getDelegate().deleteBoardGame(id);
            }


            /**
            * GET /boardgames : Get all boardgames
            *
            * @return OK (status code 200)
            */
                @Operation(
                operationId = "getAllBoardGames",
                    summary = "Get all boardgames",
                responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BoardGameDto.class)))
                    })
                }
                )
            @PreAuthorize("hasAuthority('')")
            @RequestMapping(
            method = RequestMethod.GET,
            value = "/boardgames",
            produces = { "application/json" }
            )
        default ResponseEntity<List<BoardGameDto>> getAllBoardGames(
        
            ) {
            return getDelegate().getAllBoardGames();
            }


            /**
            * GET /boardgames/{id} : Get a boardgame by id
            *
                * @param id ID of the boardgame (required)
            * @return OK (status code 200)
                *         or Not Found (status code 404)
            */
                @Operation(
                operationId = "getBoardGameById",
                    summary = "Get a boardgame by id",
                responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = BoardGameDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found")
                }
                )
            @PreAuthorize("hasAuthority('')")
            @RequestMapping(
            method = RequestMethod.GET,
            value = "/boardgames/{id}",
            produces = { "application/json" }
            )
        default ResponseEntity<BoardGameDto> getBoardGameById(
        @Parameter(name = "id", description = "ID of the boardgame", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
            ) {
            return getDelegate().getBoardGameById(id);
            }


            /**
            * PUT /boardgames/{id} : Update a boardgame
            *
                * @param id ID of the boardgame to update (required)
                * @param boardGameDto Boardgame to update (required)
            * @return OK (status code 200)
                *         or Not Found (status code 404)
            */
                @Operation(
                operationId = "updateBoardGame",
                    summary = "Update a boardgame",
                responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = BoardGameDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found")
                }
                )
            @PreAuthorize("hasAuthority('')")
            @RequestMapping(
            method = RequestMethod.PUT,
            value = "/boardgames/{id}",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<BoardGameDto> updateBoardGame(
        @Parameter(name = "id", description = "ID of the boardgame to update", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(name = "BoardGameDto", description = "Boardgame to update", required = true) @Valid @RequestBody BoardGameDto boardGameDto
            ) {
            return getDelegate().updateBoardGame(id, boardGameDto);
            }

        }
