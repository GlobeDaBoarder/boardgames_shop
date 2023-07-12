package generated.user.api;

import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.response.AddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAnyUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateCustomerUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateEmailDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePasswordDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdatePhoneDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateUsernameDto;
import ua.rivnegray.boardgames_shop.DTO.response.UserPublicDto;
import ua.rivnegray.boardgames_shop.DTO.response.UserRoleDto;
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
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-12T23:53:05.783915816+03:00[Europe/Kiev]")
public interface UsersApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /users/{userId}/address : Add an address to a user
     *
     * @param userId  (required)
     * @param addAndUpdateAddressDto  (required)
     * @return address added (status code 201)
     * @see UsersApi#addAddress
     */
    default ResponseEntity<UserPublicDto> addAddress(Long userId,
        AddAndUpdateAddressDto addAndUpdateAddressDto) {
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
     * POST /users/availability/email : Check if email is available
     *
     * @param updateEmailDto  (required)
     * @return checked for availability of email successfully (status code 204)
     *         or email already taken (status code 409)
     * @see UsersApi#checkEmailAvailability
     */
    default ResponseEntity<Boolean> checkEmailAvailability(UpdateEmailDto updateEmailDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /users/availability/username : Check if username is available
     *
     * @param updateUsernameDto  (required)
     * @return checked for availability of username successfully (status code 204)
     *         or username already taken (status code 409)
     * @see UsersApi#checkUsernameAvailability
     */
    default ResponseEntity<Boolean> checkUsernameAvailability(UpdateUsernameDto updateUsernameDto) {
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
     * GET /users/{userId}/addresses/{addressId} : Get a user&#39;s address by address id
     *
     * @param userId  (required)
     * @param addressId  (required)
     * @return The address was found (status code 200)
     * @see UsersApi#getAddressById
     */
    default ResponseEntity<AddressDto> getAddressById(Long userId,
        Long addressId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users/{userId}/addresses : Get all addresses of a user
     *
     * @param userId  (required)
     * @return All addresses were retrieved successfully (status code 200)
     * @see UsersApi#getAllAddresses
     */
    default ResponseEntity<List<AddressDto>> getAllAddresses(Long userId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 }, { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 6 } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users/roles : Get all user roles
     *
     * @return successful operation (status code 200)
     * @see UsersApi#getAllUserRoles
     */
    default ResponseEntity<List<UserRoleDto>> getAllUserRoles() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 }, { \"permissions\" : [ null, null ], \"roleName\" : \"roleName\", \"id\" : 0 } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
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
     * GET /users/role/{role} : Get users by role
     *
     * @param role  (required)
     * @return Users wih role found (status code 204)
     * @see UsersApi#getUsersByRole
     */
    default ResponseEntity<List<UserPublicDto>> getUsersByRole(String role) {
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
     * PATCH /users/{userId}/address/{addressId} : Update a user&#39;s address
     *
     * @param userId  (required)
     * @param addressId  (required)
     * @param addAndUpdateAddressDto  (required)
     * @return address updated (status code 200)
     * @see UsersApi#updateAddress
     */
    default ResponseEntity<UserPublicDto> updateAddress(Long userId,
        Long addressId,
        AddAndUpdateAddressDto addAndUpdateAddressDto) {
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
     * PATCH /users/{userId}/email : Update a user&#39;s email
     *
     * @param userId  (required)
     * @param updateEmailDto  (required)
     * @return email updated (status code 200)
     * @see UsersApi#updateEmail
     */
    default ResponseEntity<UserPublicDto> updateEmail(Long userId,
        UpdateEmailDto updateEmailDto) {
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
     * PATCH /users/{userId}/password : Update a user&#39;s password
     *
     * @param userId  (required)
     * @param updatePasswordDto  (required)
     * @return password updated (status code 200)
     * @see UsersApi#updatePassword
     */
    default ResponseEntity<UserPublicDto> updatePassword(Long userId,
        UpdatePasswordDto updatePasswordDto) {
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
     * PATCH /users/{userId}/phone : Update a user&#39;s phone
     *
     * @param userId  (required)
     * @param updatePhoneDto  (required)
     * @return phone number updated (status code 200)
     * @see UsersApi#updatePhone
     */
    default ResponseEntity<UserPublicDto> updatePhone(Long userId,
        UpdatePhoneDto updatePhoneDto) {
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
     * PATCH /users/{userId}/username : Update a user&#39;s username
     *
     * @param userId  (required)
     * @param updateUsernameDto  (required)
     * @return username updated (status code 200)
     * @see UsersApi#updateUsername
     */
    default ResponseEntity<UserPublicDto> updateUsername(Long userId,
        UpdateUsernameDto updateUsernameDto) {
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

}
