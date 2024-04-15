package generated.session.api;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
@Controller
@RequestMapping("${openapi.title.base-path:}")
public class RegisterApiController implements RegisterApi {

    private final RegisterApiDelegate delegate;

    public RegisterApiController(@Autowired(required = false) RegisterApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new RegisterApiDelegate() {});
    }

    @Override
    public RegisterApiDelegate getDelegate() {
        return delegate;
    }

}
