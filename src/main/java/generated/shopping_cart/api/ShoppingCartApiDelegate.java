package generated.shopping_cart.api;

import jakarta.annotation.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import ua.rivnegray.boardgames_shop.DTO.request.create.MapProductInCartCartDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateQuantityOfProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;

import java.util.List;
import java.util.Optional;

/**
 * A delegate to be called by the {@link ShoppingCartApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public interface ShoppingCartApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /shoppingCart/{productId} : Adds a product to the shopping cart
     *
     * @param productId  (required)
     * @return Product added successfully (status code 200)
     *         or Product not found (status code 404)
     *         or Unauthorized (status code 401)
     * @see ShoppingCartApi#addProductToMyShoppingCart
     */
    default ResponseEntity<List<ProductInShoppingCartDto>> addProductToMyShoppingCart(Long productId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"emptyValue\" : \"emptyValue\" }, { \"emptyValue\" : \"emptyValue\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /shoppingCart/checkout/{addressId} : Checkout the shopping cart and create an order for registered user
     *
     * @param addressId  (required)
     * @return Order created successfully (status code 201)
     *         or Invalid checkout request (status code 400)
     *         or Shopping cart or address not found (status code 404)
     *         or Unauthorized (status code 401)
     * @see ShoppingCartApi#checkoutRegisteredUser
     */
    default ResponseEntity<OrderDto> checkoutRegisteredUser(Long addressId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"emptyValue\" : \"emptyValue\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /shoppingCart/ : Clear all items from my shopping cart
     *
     * @return Shopping cart cleared successfully (status code 204)
     *         or Unauthorized (status code 401)
     * @see ShoppingCartApi#clearMyShoppingCart
     */
    default ResponseEntity<Void> clearMyShoppingCart() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /shoppingCart/ : Get all products in my shopping cart
     *
     * @return Products in the shopping cart (status code 200)
     *         or Unauthorized (status code 401)
     * @see ShoppingCartApi#getProductsInMyShoppingCart
     */
    default ResponseEntity<List<ProductInShoppingCartDto>> getProductsInMyShoppingCart() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"emptyValue\" : \"emptyValue\" }, { \"emptyValue\" : \"emptyValue\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /shoppingCart/{cartId} : Get all products in the shopping cart
     *
     * @param cartId  (required)
     * @return Products in the shopping cart (status code 200)
     *         or Invalid shopping cart ID (status code 400)
     * @deprecated
     * @see ShoppingCartApi#getProductsInShoppingCart
     */
    @Deprecated
    default ResponseEntity<List<ProductInShoppingCartDto>> getProductsInShoppingCart(Long cartId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"emptyValue\" : \"emptyValue\" }, { \"emptyValue\" : \"emptyValue\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /shoppingCart/map : Map cart from one stored in local storage to one stored in database
     * This operation is used when a decides to log in or register. Prior to registration or logging in all the shopping cart dat ais stored inside of localStorage. Upon login,  this allows to save client&#39;s products in the database and clear the localStorage
     *
     * @param mapProductInCartCartDto  (required)
     * @return Cart mapped successfully (status code 200)
     *         or Invalid cart data (status code 400)
     *         or Product not found (status code 404)
     *         or Unauthorized (status code 401)
     * @see ShoppingCartApi#mapCart
     */
    default ResponseEntity<List<ProductInShoppingCartDto>> mapCart(List<MapProductInCartCartDto> mapProductInCartCartDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"emptyValue\" : \"emptyValue\" }, { \"emptyValue\" : \"emptyValue\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /shoppingCart/{productInCartId} : Remove a product from the shopping cart
     *
     * @param productInCartId  (required)
     * @return Product removed successfully (status code 200)
     *         or ProductInCart not found (status code 404)
     *         or Unauthorized (status code 401)
     * @see ShoppingCartApi#removeProductFromMyShoppingCart
     */
    default ResponseEntity<List<ProductInShoppingCartDto>> removeProductFromMyShoppingCart(Long productInCartId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"emptyValue\" : \"emptyValue\" }, { \"emptyValue\" : \"emptyValue\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PATCH /shoppingCart/{productInCartId} : Update the quantity of a product in the shopping cart
     *
     * @param productInCartId  (required)
     * @param updateQuantityOfProductInShoppingCartDto  (required)
     * @return Product quantity updated successfully (status code 200)
     *         or ProductInCartId not found (status code 404)
     *         or Unauthorized (status code 401)
     * @see ShoppingCartApi#updateQuantityOfProductInMyShoppingCart
     */
    default ResponseEntity<List<ProductInShoppingCartDto>> updateQuantityOfProductInMyShoppingCart(Long productInCartId,
        UpdateQuantityOfProductInShoppingCartDto updateQuantityOfProductInShoppingCartDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"emptyValue\" : \"emptyValue\" }, { \"emptyValue\" : \"emptyValue\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
