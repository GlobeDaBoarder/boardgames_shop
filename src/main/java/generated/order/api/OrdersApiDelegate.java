package generated.order.api;

import ua.rivnegray.boardgames_shop.DTO.request.create.CreateOrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.model.OrderStatus;
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
 * A delegate to be called by the {@link OrdersApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-08-02T10:51:04.786766598+03:00[Europe/Kiev]")
public interface OrdersApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * PATCH /orders/me/{orderId} : Cancel my order
     * Cancels an order placed by the currently logged-in user
     *
     * @param orderId The ID of the order (required)
     * @return Order cancelled successfully (status code 204)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Order not found (status code 404)
     * @see OrdersApi#cancelMyOrder
     */
    default ResponseEntity<Void> cancelMyOrder(Long orderId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /orders : Creates a new order
     *
     * @param createOrderDto  (required)
     * @return Order created successfully (status code 201)
     *         or Invalid request (status code 400)
     * @see OrdersApi#createOrder
     */
    default ResponseEntity<OrderDto> createOrder(CreateOrderDto createOrderDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"dateCreated\" : \"2000-01-23T04:56:07.000+00:00\", \"address\" : { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 7 }, \"totalPrice\" : 2.302136, \"dateOrderDelivered\" : \"2000-01-23T04:56:07.000+00:00\", \"userProfileId\" : 6, \"dateOrderPlaced\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0, \"orderItems\" : [ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ], \"orderDate\" : \"2000-01-23T04:56:07.000+00:00\", \"paymentStatus\" : { \"paymentStatus\" : \"PAID\" }, \"dateUpdated\" : \"2000-01-23T04:56:07.000+00:00\", \"status\" : { \"orderStatus\" : \"PLACED\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /orders : Retrieves a list of all orders
     *
     * @return Successfully retrieved orders (status code 200)
     * @see OrdersApi#getAllOrders
     */
    default ResponseEntity<List<OrderDto>> getAllOrders() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"dateCreated\" : \"2000-01-23T04:56:07.000+00:00\", \"address\" : { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 7 }, \"totalPrice\" : 2.302136, \"dateOrderDelivered\" : \"2000-01-23T04:56:07.000+00:00\", \"userProfileId\" : 6, \"dateOrderPlaced\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0, \"orderItems\" : [ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ], \"orderDate\" : \"2000-01-23T04:56:07.000+00:00\", \"paymentStatus\" : { \"paymentStatus\" : \"PAID\" }, \"dateUpdated\" : \"2000-01-23T04:56:07.000+00:00\", \"status\" : { \"orderStatus\" : \"PLACED\" } }, { \"dateCreated\" : \"2000-01-23T04:56:07.000+00:00\", \"address\" : { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 7 }, \"totalPrice\" : 2.302136, \"dateOrderDelivered\" : \"2000-01-23T04:56:07.000+00:00\", \"userProfileId\" : 6, \"dateOrderPlaced\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0, \"orderItems\" : [ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ], \"orderDate\" : \"2000-01-23T04:56:07.000+00:00\", \"paymentStatus\" : { \"paymentStatus\" : \"PAID\" }, \"dateUpdated\" : \"2000-01-23T04:56:07.000+00:00\", \"status\" : { \"orderStatus\" : \"PLACED\" } } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /orders/me/{orderId} : Get a specific order of mine
     * Retrieves information about a specific order placed by the currently logged-in user
     *
     * @param orderId The ID of the order (required)
     * @return Order retrieved successfully (status code 200)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Order not found (status code 404)
     * @see OrdersApi#getMyOrderById
     */
    default ResponseEntity<OrderDto> getMyOrderById(Long orderId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"dateCreated\" : \"2000-01-23T04:56:07.000+00:00\", \"address\" : { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 7 }, \"totalPrice\" : 2.302136, \"dateOrderDelivered\" : \"2000-01-23T04:56:07.000+00:00\", \"userProfileId\" : 6, \"dateOrderPlaced\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0, \"orderItems\" : [ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ], \"orderDate\" : \"2000-01-23T04:56:07.000+00:00\", \"paymentStatus\" : { \"paymentStatus\" : \"PAID\" }, \"dateUpdated\" : \"2000-01-23T04:56:07.000+00:00\", \"status\" : { \"orderStatus\" : \"PLACED\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /orders/me : Get all of my orders
     * Retrieves a list of all orders placed by the currently logged-in user
     *
     * @return Successfully retrieved orders (status code 200)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     * @see OrdersApi#getMyOrders
     */
    default ResponseEntity<List<OrderDto>> getMyOrders() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"dateCreated\" : \"2000-01-23T04:56:07.000+00:00\", \"address\" : { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 7 }, \"totalPrice\" : 2.302136, \"dateOrderDelivered\" : \"2000-01-23T04:56:07.000+00:00\", \"userProfileId\" : 6, \"dateOrderPlaced\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0, \"orderItems\" : [ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ], \"orderDate\" : \"2000-01-23T04:56:07.000+00:00\", \"paymentStatus\" : { \"paymentStatus\" : \"PAID\" }, \"dateUpdated\" : \"2000-01-23T04:56:07.000+00:00\", \"status\" : { \"orderStatus\" : \"PLACED\" } }, { \"dateCreated\" : \"2000-01-23T04:56:07.000+00:00\", \"address\" : { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 7 }, \"totalPrice\" : 2.302136, \"dateOrderDelivered\" : \"2000-01-23T04:56:07.000+00:00\", \"userProfileId\" : 6, \"dateOrderPlaced\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0, \"orderItems\" : [ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ], \"orderDate\" : \"2000-01-23T04:56:07.000+00:00\", \"paymentStatus\" : { \"paymentStatus\" : \"PAID\" }, \"dateUpdated\" : \"2000-01-23T04:56:07.000+00:00\", \"status\" : { \"orderStatus\" : \"PLACED\" } } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /orders/{orderId} : Retrieves information about a specific order
     *
     * @param orderId The ID of the order (required)
     * @return Order retrieved successfully (status code 200)
     *         or Order not found (status code 404)
     *         or Unauthorized (status code 401)
     * @see OrdersApi#getOrderById
     */
    default ResponseEntity<OrderDto> getOrderById(Long orderId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"dateCreated\" : \"2000-01-23T04:56:07.000+00:00\", \"address\" : { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 7 }, \"totalPrice\" : 2.302136, \"dateOrderDelivered\" : \"2000-01-23T04:56:07.000+00:00\", \"userProfileId\" : 6, \"dateOrderPlaced\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0, \"orderItems\" : [ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ], \"orderDate\" : \"2000-01-23T04:56:07.000+00:00\", \"paymentStatus\" : { \"paymentStatus\" : \"PAID\" }, \"dateUpdated\" : \"2000-01-23T04:56:07.000+00:00\", \"status\" : { \"orderStatus\" : \"PLACED\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PATCH /orders/{orderId} : Updates the status of an order
     * use PROCESSING, ACCEPTED, IN_DELIVERY, DONE, CANCELLED as possible statuses
     *
     * @param orderId The ID of the order (required)
     * @param orderStatus PROCESSING, ACCEPTED, IN_DELIVERY, DONE, CANCELLED (required)
     * @return Order status updated successfully (status code 200)
     *         or Invalid request (status code 400)
     *         or Order not found (status code 404)
     *         or Unauthorized (status code 401)
     * @see OrdersApi#updateOrderStatus
     */
    default ResponseEntity<OrderDto> updateOrderStatus(Long orderId,
        OrderStatus orderStatus) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"dateCreated\" : \"2000-01-23T04:56:07.000+00:00\", \"address\" : { \"country\" : \"country\", \"city\" : \"city\", \"street\" : \"street\", \"postalCode\" : \"postalCode\", \"houseNumber\" : \"houseNumber\", \"id\" : 7 }, \"totalPrice\" : 2.302136, \"dateOrderDelivered\" : \"2000-01-23T04:56:07.000+00:00\", \"userProfileId\" : 6, \"dateOrderPlaced\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0, \"orderItems\" : [ { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 }, { \"quantity\" : 5, \"productId\" : 5, \"id\" : 1 } ], \"orderDate\" : \"2000-01-23T04:56:07.000+00:00\", \"paymentStatus\" : { \"paymentStatus\" : \"PAID\" }, \"dateUpdated\" : \"2000-01-23T04:56:07.000+00:00\", \"status\" : { \"orderStatus\" : \"PLACED\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
