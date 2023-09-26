package generated.session.api;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Controller
@RequestMapping("${openapi.title.base-path:}")
public class LoginApiController implements LoginApi {

    private final LoginApiDelegate delegate;

    public LoginApiController(@Autowired(required = false) LoginApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new LoginApiDelegate() {});
    }

    @Override
    public LoginApiDelegate getDelegate() {
        return delegate;
    }

}
