package generated.shopping_cart.api;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.4.0")
@Controller
@RequestMapping("${openapi.shoppingCart.base-path:}")
public class ShoppingCartApiController implements ShoppingCartApi {

    private final ShoppingCartApiDelegate delegate;

    public ShoppingCartApiController(@Autowired(required = false) ShoppingCartApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new ShoppingCartApiDelegate() {});
    }

    @Override
    public ShoppingCartApiDelegate getDelegate() {
        return delegate;
    }

}
