package generated.shopping_cart.api;

import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.AddProductInShoppingCartDto;
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
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-15T13:06:43.497471985+03:00[Europe/Kiev]")
public interface ShoppingCartApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /shoppingCart/{cartId}/products : Adds a product to the shopping cart
     *
     * @param cartId  (required)
     * @param addProductInShoppingCartDto  (required)
     * @return Product added successfully (status code 201)
     *         or Invalid product data (status code 400)
     * @see ShoppingCartApi#addProductToShoppingCart
     */
    default ResponseEntity<ShoppingCartDto> addProductToShoppingCart(Long cartId,
        AddProductInShoppingCartDto addProductInShoppingCartDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"productsInShoppingCart\" : [ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ], \"userProfileId\" : 6, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /shoppingCart/{cartId}/checkout/{addressId} : Checkout the shopping cart and create an order for registered user
     *
     * @param cartId  (required)
     * @param addressId  (required)
     * @return Order created successfully (status code 201)
     *         or Invalid checkout request (status code 400)
     *         or Shopping cart or address not found (status code 404)
     *         or Unauthorized (status code 401)
     * @see ShoppingCartApi#checkoutRegisteredUser
     */
    default ResponseEntity<OrderDto> checkoutRegisteredUser(Long cartId,
        Long addressId) {
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
     * POST /shoppingCart/{cartId}/checkout : Checkout the shopping cart and create an order for unregistered user
     *
     * @param cartId  (required)
     * @param addAndUpdateAddressDto  (required)
     * @return Order created successfully (status code 201)
     *         or Invalid checkout request (status code 400)
     *         or Shopping cart not found (status code 404)
     * @see ShoppingCartApi#checkoutUnregisteredUser
     */
    default ResponseEntity<OrderDto> checkoutUnregisteredUser(Long cartId,
        AddAndUpdateAddressDto addAndUpdateAddressDto) {
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
     * DELETE /shoppingCart/{cartId} : Clear all items from the shopping cart
     *
     * @param cartId  (required)
     * @return Shopping cart cleared successfully (status code 200)
     *         or Invalid shopping cart ID (status code 400)
     * @see ShoppingCartApi#clearShoppingCart
     */
    default ResponseEntity<ShoppingCartDto> clearShoppingCart(Long cartId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"productsInShoppingCart\" : [ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ], \"userProfileId\" : 6, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /shoppingCart/{cartId}/products : Get all products in the shopping cart
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
                    String exampleString = "[ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /shoppingCart/{cartId} : get shopping cart info
     *
     * @param cartId  (required)
     * @return Shopping cart details (status code 200)
     * @see ShoppingCartApi#getShoppingCart
     */
    default ResponseEntity<ShoppingCartDto> getShoppingCart(Long cartId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"productsInShoppingCart\" : [ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ], \"userProfileId\" : 6, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /shoppingCart/{cartId}/products/{productInCartId} : Remove a product from the shopping cart
     *
     * @param cartId  (required)
     * @param productInCartId  (required)
     * @return Product removed successfully (status code 200)
     *         or Invalid product or shopping cart ID (status code 400)
     * @see ShoppingCartApi#removeProductFromShoppingCart
     */
    default ResponseEntity<ShoppingCartDto> removeProductFromShoppingCart(Long cartId,
        Long productInCartId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"productsInShoppingCart\" : [ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ], \"userProfileId\" : 6, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PATCH /shoppingCart/{cartId}/products/{productInCartId} : Update the quantity of a product in the shopping cart
     *
     * @param cartId  (required)
     * @param productInCartId  (required)
     * @param updateQuantityOfProductInShoppingCartDto  (required)
     * @return Product quantity updated successfully (status code 200)
     *         or Invalid product data (status code 400)
     * @see ShoppingCartApi#updateQuantityOfProductInShoppingCart
     */
    default ResponseEntity<ShoppingCartDto> updateQuantityOfProductInShoppingCart(Long cartId,
        Long productInCartId,
        UpdateQuantityOfProductInShoppingCartDto updateQuantityOfProductInShoppingCartDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"productsInShoppingCart\" : [ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ], \"userProfileId\" : 6, \"id\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
