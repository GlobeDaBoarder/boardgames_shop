package generated.user.api;

import ua.rivnegray.boardgames_shop.model.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegate to be called by the {@link UsersApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-10T09:58:52.279836093+03:00[Europe/Kiev]")
public interface UsersApiDelegate {

    Logger log = LoggerFactory.getLogger(UsersApi.class);

    default Optional<ObjectMapper> getObjectMapper(){
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest(){
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    /**
     * @see UsersApi#deleteUser
     */
    default ResponseEntity<User> deleteUser( Long  id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"password\" : \"password\",\n  \"phone\" : \"phone\",\n  \"imageUrl\" : \"imageUrl\",\n  \"roles\" : [ {\n    \"permissions\" : [ \"USER_READ\", \"USER_READ\" ],\n    \"roleName\" : \"roleName\",\n    \"id\" : 6\n  }, {\n    \"permissions\" : [ \"USER_READ\", \"USER_READ\" ],\n    \"roleName\" : \"roleName\",\n    \"id\" : 6\n  } ],\n  \"id\" : 0,\n  \"email\" : \"email\",\n  \"username\" : \"username\"\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see UsersApi#getAllUsers
     */
    default ResponseEntity<List<User>> getAllUsers() {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("[ {\n  \"password\" : \"password\",\n  \"phone\" : \"phone\",\n  \"imageUrl\" : \"imageUrl\",\n  \"roles\" : [ {\n    \"permissions\" : [ \"USER_READ\", \"USER_READ\" ],\n    \"roleName\" : \"roleName\",\n    \"id\" : 6\n  }, {\n    \"permissions\" : [ \"USER_READ\", \"USER_READ\" ],\n    \"roleName\" : \"roleName\",\n    \"id\" : 6\n  } ],\n  \"id\" : 0,\n  \"email\" : \"email\",\n  \"username\" : \"username\"\n}, {\n  \"password\" : \"password\",\n  \"phone\" : \"phone\",\n  \"imageUrl\" : \"imageUrl\",\n  \"roles\" : [ {\n    \"permissions\" : [ \"USER_READ\", \"USER_READ\" ],\n    \"roleName\" : \"roleName\",\n    \"id\" : 6\n  }, {\n    \"permissions\" : [ \"USER_READ\", \"USER_READ\" ],\n    \"roleName\" : \"roleName\",\n    \"id\" : 6\n  } ],\n  \"id\" : 0,\n  \"email\" : \"email\",\n  \"username\" : \"username\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see UsersApi#getUserById
     */
    default ResponseEntity<User> getUserById( Long  id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"password\" : \"password\",\n  \"phone\" : \"phone\",\n  \"imageUrl\" : \"imageUrl\",\n  \"roles\" : [ {\n    \"permissions\" : [ \"USER_READ\", \"USER_READ\" ],\n    \"roleName\" : \"roleName\",\n    \"id\" : 6\n  }, {\n    \"permissions\" : [ \"USER_READ\", \"USER_READ\" ],\n    \"roleName\" : \"roleName\",\n    \"id\" : 6\n  } ],\n  \"id\" : 0,\n  \"email\" : \"email\",\n  \"username\" : \"username\"\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see UsersApi#updateUser
     */
    default ResponseEntity<User> updateUser( Long  id,
         User  body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\n  \"password\" : \"password\",\n  \"phone\" : \"phone\",\n  \"imageUrl\" : \"imageUrl\",\n  \"roles\" : [ {\n    \"permissions\" : [ \"USER_READ\", \"USER_READ\" ],\n    \"roleName\" : \"roleName\",\n    \"id\" : 6\n  }, {\n    \"permissions\" : [ \"USER_READ\", \"USER_READ\" ],\n    \"roleName\" : \"roleName\",\n    \"id\" : 6\n  } ],\n  \"id\" : 0,\n  \"email\" : \"email\",\n  \"username\" : \"username\"\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UsersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
