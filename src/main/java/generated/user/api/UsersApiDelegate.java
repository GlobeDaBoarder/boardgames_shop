package generated.user.api;

import ua.rivnegray.boardgames_shop.DTO.request.CreateAndUpdateUserDto;
import ua.rivnegray.boardgames_shop.DTO.response.UserDto;
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
 * A delegate to be called by the {@link UsersApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-22T09:30:53.598617247+03:00[Europe/Kiev]")
public interface UsersApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /users : Create a new user
     *
     * @param createAndUpdateUserDto  (required)
     * @return User created (status code 201)
     *         or Invalid input (status code 400)
     * @see UsersApi#createUser
     */
    default ResponseEntity<UserDto> createUser(CreateAndUpdateUserDto createAndUpdateUserDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"password\" : \"password\", \"phone\" : \"phone\", \"imageUrl\" : \"imageUrl\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 6 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 6 } ], \"id\" : 0, \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /users/{id} : Delete user
     *
     * @param id  (required)
     * @return User deleted (status code 204)
     *         or User not found (status code 404)
     * @see UsersApi#deleteUser
     */
    default ResponseEntity<Void> deleteUser(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users : Get all users
     *
     * @return successful operation (status code 200)
     * @see UsersApi#getAllUsers
     */
    default ResponseEntity<List<UserDto>> getAllUsers() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"password\" : \"password\", \"phone\" : \"phone\", \"imageUrl\" : \"imageUrl\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 6 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 6 } ], \"id\" : 0, \"email\" : \"email\", \"username\" : \"username\" }, { \"password\" : \"password\", \"phone\" : \"phone\", \"imageUrl\" : \"imageUrl\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 6 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 6 } ], \"id\" : 0, \"email\" : \"email\", \"username\" : \"username\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users/{id} : Get user by ID
     *
     * @param id  (required)
     * @return successful operation (status code 200)
     *         or User not found (status code 404)
     * @see UsersApi#getUserById
     */
    default ResponseEntity<UserDto> getUserById(Long id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"password\" : \"password\", \"phone\" : \"phone\", \"imageUrl\" : \"imageUrl\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 6 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 6 } ], \"id\" : 0, \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /users/{id} : Update user
     *
     * @param id  (required)
     * @param createAndUpdateUserDto  (required)
     * @return User updated (status code 200)
     *         or User not found (status code 404)
     * @see UsersApi#updateUser
     */
    default ResponseEntity<UserDto> updateUser(Long id,
        CreateAndUpdateUserDto createAndUpdateUserDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"password\" : \"password\", \"phone\" : \"phone\", \"imageUrl\" : \"imageUrl\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 6 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 6 } ], \"id\" : 0, \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
