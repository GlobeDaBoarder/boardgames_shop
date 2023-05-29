package generated.user.api;

import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAnyUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateCustomerUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateEmailDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateFullUserProfileDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePasswordDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePhoneDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateUsernameDto;
import ua.rivnegray.boardgames_shop.DTO.response.UserPublicDto;
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
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-29T19:15:58.460159669+03:00[Europe/Kiev]")
public interface UsersApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /users/{userId}/address : Add an address to a user
     *
     * @param userId  (required)
     * @param addAndUpdateAddressDto  (required)
     * @return address added (status code 200)
     * @see UsersApi#addAddress
     */
    default ResponseEntity<Void> addAddress(Long userId,
        AddAndUpdateAddressDto addAndUpdateAddressDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /users/customer : Create a new customer user
     *
     * @param createCustomerUserDto  (required)
     * @return User created (status code 201)
     * @see UsersApi#createCustomerUser
     */
    default ResponseEntity<UserPublicDto> createCustomerUser(CreateCustomerUserDto createCustomerUserDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /users : Create a new user with specified roles
     *
     * @param createAnyUserDto  (required)
     * @return User created (status code 201)
     * @see UsersApi#createSpecifiedUser
     */
    default ResponseEntity<UserPublicDto> createSpecifiedUser(CreateAnyUserDto createAnyUserDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /users/{userId} : Delete a user by ID
     *
     * @param userId  (required)
     * @return User deleted (status code 204)
     *         or User not found (status code 404)
     * @see UsersApi#deleteUser
     */
    default ResponseEntity<Void> deleteUser(Long userId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users : Get all users
     *
     * @return successful operation (status code 200)
     * @see UsersApi#getAllUsersPublicInfo
     */
    default ResponseEntity<List<UserPublicDto>> getAllUsersPublicInfo() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }, { \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users/{userId} : Get a user by ID
     *
     * @param userId  (required)
     * @return successful operation (status code 200)
     *         or User not found (status code 404)
     * @see UsersApi#getUserPublicInfoById
     */
    default ResponseEntity<UserPublicDto> getUserPublicInfoById(Long userId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /users/{userId}/address/{addressId} : Remove an address from a user
     *
     * @param userId  (required)
     * @param addressId  (required)
     * @return address deleted (status code 204)
     *         or address not founf (status code 404)
     * @see UsersApi#removeAddress
     */
    default ResponseEntity<Void> removeAddress(Long userId,
        Long addressId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /users/{userId}/address : Update a user&#39;s address
     *
     * @param userId  (required)
     * @param addAndUpdateAddressDto  (required)
     * @return address updated (status code 200)
     * @see UsersApi#updateAddress
     */
    default ResponseEntity<Void> updateAddress(Long userId,
        AddAndUpdateAddressDto addAndUpdateAddressDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /users/{userId}/email : Update a user&#39;s email
     *
     * @param userId  (required)
     * @param updateEmailDto  (required)
     * @return email updated (status code 200)
     * @see UsersApi#updateEmail
     */
    default ResponseEntity<Void> updateEmail(Long userId,
        UpdateEmailDto updateEmailDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /users/{userId} : Update a user&#39;s full profile
     *
     * @param userId  (required)
     * @param updateFullUserProfileDto  (required)
     * @return User updated (status code 200)
     *         or User not found (status code 404)
     * @see UsersApi#updateFullUserProfile
     */
    default ResponseEntity<UserPublicDto> updateFullUserProfile(Long userId,
        UpdateFullUserProfileDto updateFullUserProfileDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"addresses\" : [ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ], \"phone\" : \"phone\", \"roles\" : [ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ], \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /users/{userId}/password : Update a user&#39;s password
     *
     * @param userId  (required)
     * @param updatePasswordDto  (required)
     * @return password updated (status code 200)
     * @see UsersApi#updatePassword
     */
    default ResponseEntity<Void> updatePassword(Long userId,
        UpdatePasswordDto updatePasswordDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /users/{userId}/phone : Update a user&#39;s phone
     *
     * @param userId  (required)
     * @param updatePhoneDto  (required)
     * @return phone number updated (status code 200)
     * @see UsersApi#updatePhone
     */
    default ResponseEntity<Void> updatePhone(Long userId,
        UpdatePhoneDto updatePhoneDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /users/{userId}/username : Update a user&#39;s username
     *
     * @param userId  (required)
     * @param updateUsernameDto  (required)
     * @return username updated (status code 200)
     * @see UsersApi#updateUsername
     */
    default ResponseEntity<Void> updateUsername(Long userId,
        UpdateUsernameDto updateUsernameDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
