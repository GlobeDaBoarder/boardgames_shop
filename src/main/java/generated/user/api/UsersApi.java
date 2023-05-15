/**
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package generated.user.api;

import ua.rivnegray.boardgames_shop.DTO.UserDto;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-15T07:37:47.099710385+03:00[Europe/Kiev]")
    @Validated
    @Tag(name = "users", description = "the users API")
    public interface UsersApi {

                default UsersApiDelegate getDelegate() {
                return new UsersApiDelegate() {};
                }

            /**
            * POST /users : Create a new user
            *
                * @param userDto  (required)
            * @return User created (status code 201)
                *         or Invalid input (status code 400)
            */
                @Operation(
                operationId = "createUser",
                    summary = "Create a new user",
                responses = {
                    @ApiResponse(responseCode = "201", description = "User created", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
                }
                )
            @RequestMapping(
            method = RequestMethod.POST,
            value = "/users",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<UserDto> createUser(
        @Parameter(name = "UserDto", description = "", required = true) @Valid @RequestBody UserDto userDto
            ) {
            return getDelegate().createUser(userDto);
            }


            /**
            * DELETE /users/{id} : Delete user
            *
                * @param id  (required)
            * @return User deleted (status code 204)
                *         or User not found (status code 404)
            */
                @Operation(
                operationId = "deleteUser",
                    summary = "Delete user",
                responses = {
                    @ApiResponse(responseCode = "204", description = "User deleted"),
                    @ApiResponse(responseCode = "404", description = "User not found")
                }
                )
            @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/users/{id}"
            )
        default ResponseEntity<Void> deleteUser(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
            ) {
            return getDelegate().deleteUser(id);
            }


            /**
            * GET /users : Get all users
            *
            * @return successful operation (status code 200)
            */
                @Operation(
                operationId = "getAllUsers",
                    summary = "Get all users",
                responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
                    })
                }
                )
            @RequestMapping(
            method = RequestMethod.GET,
            value = "/users",
            produces = { "application/json" }
            )
        default ResponseEntity<List<UserDto>> getAllUsers(
        
            ) {
            return getDelegate().getAllUsers();
            }


            /**
            * GET /users/{id} : Get user by ID
            *
                * @param id  (required)
            * @return successful operation (status code 200)
                *         or User not found (status code 404)
            */
                @Operation(
                operationId = "getUserById",
                    summary = "Get user by ID",
                responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "User not found")
                }
                )
            @RequestMapping(
            method = RequestMethod.GET,
            value = "/users/{id}",
            produces = { "application/json" }
            )
        default ResponseEntity<UserDto> getUserById(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
            ) {
            return getDelegate().getUserById(id);
            }


            /**
            * PUT /users/{id} : Update user
            *
                * @param id  (required)
                * @param userDto  (required)
            * @return User updated (status code 200)
                *         or User not found (status code 404)
            */
                @Operation(
                operationId = "updateUser",
                    summary = "Update user",
                responses = {
                    @ApiResponse(responseCode = "200", description = "User updated", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "User not found")
                }
                )
            @RequestMapping(
            method = RequestMethod.PUT,
            value = "/users/{id}",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<UserDto> updateUser(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "UserDto", description = "", required = true) @Valid @RequestBody UserDto userDto
            ) {
            return getDelegate().updateUser(id, userDto);
            }

        }
