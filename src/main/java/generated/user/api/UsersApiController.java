package generated.user.api;

import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-10T09:58:52.279836093+03:00[Europe/Kiev]")
@RestController
public class UsersApiController implements UsersApi {

    private final UsersApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(UsersApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public UsersApiDelegate getDelegate() {
        return delegate;
    }
}
