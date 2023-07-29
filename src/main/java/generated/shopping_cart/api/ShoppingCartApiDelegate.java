package generated.shopping_cart.api;

import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.ShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateQuantityOfProductInShoppingCartDto;
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
 * A delegate to be called by the {@link ShoppingCartApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-27T15:23:44.028897608+03:00[Europe/Kiev]")
public interface ShoppingCartApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /shoppingCart/{productId} : Adds a product to the shopping cart
     *
     * @param productId  (required)
     * @return Product added successfully (status code 201)
     *         or Invalid product data (status code 400)
     * @see ShoppingCartApi#addProductToMyShoppingCart
     */
    default ResponseEntity<ShoppingCartDto> addProductToMyShoppingCart(Long productId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"productsInShoppingCart\" : [ { \"quantity\" : 1, \"productId\" : 6, \"id\" : 0 }, { \"quantity\" : 1, \"productId\" : 6, \"id\" : 0 } ], \"userProfileId\" : 6, \"id\" : 0 }";
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
                    String exampleString = "{ \"dateCreated\" : \"2000-01-23T04:56:07.000+00:00\", \"address\" : { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\" }, \"totalPrice\" : 2.302136, \"dateOrderDelivered\" : \"2000-01-23T04:56:07.000+00:00\", \"userProfileId\" : 6, \"dateOrderPlaced\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0, \"orderItems\" : [ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ], \"orderDate\" : \"2000-01-23T04:56:07.000+00:00\", \"paymentStatus\" : { \"paymentStatus\" : \"PAID\" }, \"dateUpdated\" : \"2000-01-23T04:56:07.000+00:00\", \"status\" : { \"orderStatus\" : \"PLACED\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /shoppingCart/checkout : Checkout the shopping cart and create an order for unregistered user. WARNING: this endpoint is not yet implemented.
     *
     * @param addAndUpdateAddressDto  (required)
     * @return Not implemented (status code 501)
     *         or Order created successfully (status code 201)
     * @deprecated
     * @see ShoppingCartApi#checkoutUnregisteredUser
     */
    @Deprecated
    default ResponseEntity<OrderDto> checkoutUnregisteredUser(AddAndUpdateAddressDto addAndUpdateAddressDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"dateCreated\" : \"2000-01-23T04:56:07.000+00:00\", \"address\" : { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\" }, \"totalPrice\" : 2.302136, \"dateOrderDelivered\" : \"2000-01-23T04:56:07.000+00:00\", \"userProfileId\" : 6, \"dateOrderPlaced\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0, \"orderItems\" : [ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ], \"orderDate\" : \"2000-01-23T04:56:07.000+00:00\", \"paymentStatus\" : { \"paymentStatus\" : \"PAID\" }, \"dateUpdated\" : \"2000-01-23T04:56:07.000+00:00\", \"status\" : { \"orderStatus\" : \"PLACED\" } }";
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
     * @return Shopping cart cleared successfully (status code 200)
     *         or Invalid shopping cart ID (status code 400)
     * @see ShoppingCartApi#clearMyShoppingCart
     */
    default ResponseEntity<ShoppingCartDto> clearMyShoppingCart() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"productsInShoppingCart\" : [ { \"quantity\" : 1, \"productId\" : 6, \"id\" : 0 }, { \"quantity\" : 1, \"productId\" : 6, \"id\" : 0 } ], \"userProfileId\" : 6, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /shoppingCart/ : Get all products in my shopping cart
     *
     * @return Products in the shopping cart (status code 200)
     *         or Invalid shopping cart ID (status code 400)
     * @see ShoppingCartApi#getProductsInMyShoppingCart
     */
    default ResponseEntity<List<ProductInShoppingCartDto>> getProductsInMyShoppingCart() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"quantity\" : 1, \"productId\" : 6, \"id\" : 0 }, { \"quantity\" : 1, \"productId\" : 6, \"id\" : 0 } ]";
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
     * @see ShoppingCartApi#getProductsInShoppingCart
     */
    default ResponseEntity<List<ProductInShoppingCartDto>> getProductsInShoppingCart(Long cartId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"quantity\" : 1, \"productId\" : 6, \"id\" : 0 }, { \"quantity\" : 1, \"productId\" : 6, \"id\" : 0 } ]";
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
     *         or Invalid product or shopping cart ID (status code 400)
     * @see ShoppingCartApi#removeProductFromMyShoppingCart
     */
    default ResponseEntity<ShoppingCartDto> removeProductFromMyShoppingCart(Long productInCartId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"productsInShoppingCart\" : [ { \"quantity\" : 1, \"productId\" : 6, \"id\" : 0 }, { \"quantity\" : 1, \"productId\" : 6, \"id\" : 0 } ], \"userProfileId\" : 6, \"id\" : 0 }";
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
     *         or Invalid product data (status code 400)
     * @see ShoppingCartApi#updateQuantityOfProductInMyShoppingCart
     */
    default ResponseEntity<ShoppingCartDto> updateQuantityOfProductInMyShoppingCart(Long productInCartId,
        UpdateQuantityOfProductInShoppingCartDto updateQuantityOfProductInShoppingCartDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"productsInShoppingCart\" : [ { \"quantity\" : 1, \"productId\" : 6, \"id\" : 0 }, { \"quantity\" : 1, \"productId\" : 6, \"id\" : 0 } ], \"userProfileId\" : 6, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
