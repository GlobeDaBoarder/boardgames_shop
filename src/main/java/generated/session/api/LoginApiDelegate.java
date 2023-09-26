package generated.session.api;

import jakarta.annotation.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import ua.rivnegray.boardgames_shop.DTO.request.LoginRequestDto;
import ua.rivnegray.boardgames_shop.DTO.response.TokenDto;

import java.util.Optional;

/**
 * A delegate to be called by the {@link LoginApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public interface LoginApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /login : Log in a user
     *
     * @param loginRequestDto  (required)
     * @return Successful operation (status code 200)
     *         or Bad Request (status code 400)
     *         or Conflict, e.g., email already in use (status code 409)
     * @see LoginApi#loginUser
     */
    default ResponseEntity<TokenDto> loginUser(LoginRequestDto loginRequestDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"token\" : \"eyJraWQiOiI3N2VlNDlkOC02N2Q3LTQ0MDktODk0YS1jM2IzMjM2MTMwZjIiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInNjb3BlIjpbImdlbnJlQW5kTWVjaGFuaWM6Y3JlYXRlIiwiYm9hcmRHYW1lOmRlbGV0ZSIsInVzZXI6ZGVsZXRlIiwidXNlcjp1cGRhdGVNZSIsInVzZXI6cmVhZE1lIiwib3JkZXI6ZXhwb3J0Iiwib3JkZXI6cmVhZCIsInVzZXI6dXBkYXRlIiwiYm9hcmRHYW1lOmFyY2hpdmVBbmRVbmFyY2hpdmUiLCJzaG9wcGluZ0NhcnQ6bWFuYWdlTWUiLCJvcmRlcjpjcmVhdGUiLCJib2FyZEdhbWU6dXBkYXRlIiwiZ2VucmVBbmRNZWNoYW5pYzpkZWxldGUiLCJnZW5yZUFuZE1lY2hhbmljOnVwZGF0ZSIsIm9yZGVyOmRlbGV0ZSIsInVzZXI6Y3JlYXRlIiwib3JkZXI6dXBkYXRlU3RhdHVzIiwiYm9hcmRHYW1lOmNyZWF0ZSIsInVzZXI6cmVhZCIsImJvYXJkR2FtZTpyZWFkQXJjaGl2ZWQiLCJvcmRlcjpyZWFkTWUiLCJvcmRlcjpjYW5jZWxNZSIsInNob3BwaW5nQ2FydDpyZWFkIl0sImlzcyI6InNlbGYiLCJpZCI6MSwiZXhwIjoxNjk1NjMzNjA1LCJpYXQiOjE2OTU2MzAwMDV9.Ln4RALpfhAliwqc2bvDC7quQB0KnrkdFr9t8wuUnD6K38k_69-ApcMl2J9vexA1DtUq6IsuFwyeTEH9n72p5h-KnwJ8pMmm6DgN4g4YytFBgK0rMrMFdFMgx3LD9JIpqiUuYe1M_ItsxFhrgYZAP1Sa6c5GeRLSrkxVz0tB8nHHZ_qd2Ihj1g8aTwzB74gX_IbpaGkO0ymmTKC4ZHVCSdKWP9Gi-TV67xFi0Vr78mVv9-JhV-Li_t98xiwOF3oNkDsDhl0FRyPRwkGZ-iDpHvulaDczp3hvGHwBavNMQf2mjBcW76M6GrH9iBA1f1Cla1SXksqx6wBFhAPRhdfOiPg\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
