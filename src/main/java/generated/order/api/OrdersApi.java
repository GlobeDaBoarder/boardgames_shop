/**
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package generated.order.api;

import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.model.OrderStatus;
    import io.swagger.v3.oas.annotations.ExternalDocumentation;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.Parameter;
    import io.swagger.v3.oas.annotations.Parameters;
    import io.swagger.v3.oas.annotations.media.ArraySchema;
    import io.swagger.v3.oas.annotations.media.Content;
    import io.swagger.v3.oas.annotations.media.Schema;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.security.SecurityRequirement;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import io.swagger.v3.oas.annotations.enums.ParameterIn;
    import org.springframework.http.ResponseEntity;
    import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

    import javax.validation.Valid;
    import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.springframework.security.access.prepost.PreAuthorize;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-09T19:13:08.300843110+03:00[Europe/Kiev]")
    @Validated
    @Tag(name = "orders", description = "the orders API")
    public interface OrdersApi {

                default OrdersApiDelegate getDelegate() {
                return new OrdersApiDelegate() {};
                }

            /**
            * DELETE /orders/{orderId} : Cancels an order
            *
                * @param orderId The ID of the order (required)
            * @return Order cancelled successfully (status code 204)
                *         or Order not found (status code 404)
            */
                @Operation(
                operationId = "cancelOrder",
                    summary = "Cancels an order",
                responses = {
                    @ApiResponse(responseCode = "204", description = "Order cancelled successfully"),
                    @ApiResponse(responseCode = "404", description = "Order not found")
                }
                )
                        @PreAuthorize("hasAuthority('user:write')")
            @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/orders/{orderId}"
            )
        default ResponseEntity<Void> cancelOrder(
        @Parameter(name = "orderId", description = "The ID of the order", required = true, in = ParameterIn.PATH) @PathVariable("orderId") Long orderId
            ) {
            return getDelegate().cancelOrder(orderId);
            }


            /**
            * GET /orders : Retrieves a list of all orders
            *
            * @return Successfully retrieved orders (status code 200)
            */
                @Operation(
                operationId = "getAllOrders",
                    summary = "Retrieves a list of all orders",
                responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved orders", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))
                    })
                }
                )
                        @PreAuthorize("hasAuthority('admin:read')")
            @RequestMapping(
            method = RequestMethod.GET,
            value = "/orders",
            produces = { "application/json" }
            )
        default ResponseEntity<List<OrderDto>> getAllOrders(
        
            ) {
            return getDelegate().getAllOrders();
            }


            /**
            * GET /orders/{orderId} : Retrieves information about a specific order
            *
                * @param orderId The ID of the order (required)
            * @return Order retrieved successfully (status code 200)
                *         or Order not found (status code 404)
            */
                @Operation(
                operationId = "getOrderById",
                    summary = "Retrieves information about a specific order",
                responses = {
                    @ApiResponse(responseCode = "200", description = "Order retrieved successfully", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Order not found")
                }
                )
                        @PreAuthorize("hasAuthority('user:read')")
            @RequestMapping(
            method = RequestMethod.GET,
            value = "/orders/{orderId}",
            produces = { "application/json" }
            )
        default ResponseEntity<OrderDto> getOrderById(
        @Parameter(name = "orderId", description = "The ID of the order", required = true, in = ParameterIn.PATH) @PathVariable("orderId") Long orderId
            ) {
            return getDelegate().getOrderById(orderId);
            }


            /**
            * PATCH /orders/{orderId} : Updates the status of an order
            *
                * @param orderId The ID of the order (required)
                * @param orderStatus PROCESSING, ACCEPTED, IN_DELIVERY, DONE, CANCELLED (required)
            * @return Order status updated successfully (status code 200)
                *         or Invalid request (status code 400)
                *         or Order not found (status code 404)
            */
                @Operation(
                operationId = "updateOrderStatus",
                    summary = "Updates the status of an order",
                responses = {
                    @ApiResponse(responseCode = "200", description = "Order status updated successfully", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Invalid request"),
                    @ApiResponse(responseCode = "404", description = "Order not found")
                }
                )
                        @PreAuthorize("hasAuthority('admin:write')")
            @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/orders/{orderId}",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<OrderDto> updateOrderStatus(
        @Parameter(name = "orderId", description = "The ID of the order", required = true, in = ParameterIn.PATH) @PathVariable("orderId") Long orderId,
        @Parameter(name = "OrderStatus", description = "PROCESSING, ACCEPTED, IN_DELIVERY, DONE, CANCELLED", required = true) @Valid @RequestBody OrderStatus orderStatus
            ) {
            return getDelegate().updateOrderStatus(orderId, orderStatus);
            }

        }
