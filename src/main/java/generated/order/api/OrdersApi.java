/**
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package generated.order.api;

import ua.rivnegray.boardgames_shop.DTO.request.create.CreateOrderDto;
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


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-27T15:23:44.259114456+03:00[Europe/Kiev]")
    @Validated
    @Tag(name = "orders", description = "the orders API")
    public interface OrdersApi {

                default OrdersApiDelegate getDelegate() {
                return new OrdersApiDelegate() {};
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
            */
                @Operation(
                operationId = "cancelMyOrder",
                    summary = "Cancel my order",
                    description = "Cancels an order placed by the currently logged-in user",
                responses = {
                    @ApiResponse(responseCode = "204", description = "Order cancelled successfully", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
                },
                security = {
                    @SecurityRequirement(name = "bearerAuth")
                }
                )
                        @PreAuthorize("hasAuthority('SCOPE_order:cancelMe')")
            @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/orders/me/{orderId}"
            )
        default ResponseEntity<Void> cancelMyOrder(
        @Parameter(name = "orderId", description = "The ID of the order", required = true, in = ParameterIn.PATH) @PathVariable("orderId") Long orderId
            ) {
            return getDelegate().cancelMyOrder(orderId);
            }


            /**
            * POST /orders : Creates a new order
            *
                * @param createOrderDto  (required)
            * @return Order created successfully (status code 201)
                *         or Invalid request (status code 400)
            */
                @Operation(
                operationId = "createOrder",
                    summary = "Creates a new order",
                responses = {
                    @ApiResponse(responseCode = "201", description = "Order created successfully", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
                },
                security = {
                    @SecurityRequirement(name = "bearerAuth")
                }
                )
                        @PreAuthorize("hasAuthority('SCOPE_order:create')")
            @RequestMapping(
            method = RequestMethod.POST,
            value = "/orders",
            produces = { "application/json" },
            consumes = { "application/json" }
            )
        default ResponseEntity<OrderDto> createOrder(
        @Parameter(name = "CreateOrderDto", description = "", required = true) @Valid @RequestBody CreateOrderDto createOrderDto
            ) {
            return getDelegate().createOrder(createOrderDto);
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
                },
                security = {
                    @SecurityRequirement(name = "bearerAuth")
                }
                )
                        @PreAuthorize("hasAuthority('SCOPE_order:read')")
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
            * GET /orders/me/{orderId} : Get a specific order of mine
                * Retrieves information about a specific order placed by the currently logged-in user
            *
                * @param orderId The ID of the order (required)
            * @return Order retrieved successfully (status code 200)
                *         or Unauthorized (status code 401)
                *         or Forbidden (status code 403)
                *         or Order not found (status code 404)
            */
                @Operation(
                operationId = "getMyOrderById",
                    summary = "Get a specific order of mine",
                    description = "Retrieves information about a specific order placed by the currently logged-in user",
                responses = {
                    @ApiResponse(responseCode = "200", description = "Order retrieved successfully", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
                },
                security = {
                    @SecurityRequirement(name = "bearerAuth")
                }
                )
                        @PreAuthorize("hasAuthority('SCOPE_order:readMe')")
            @RequestMapping(
            method = RequestMethod.GET,
            value = "/orders/me/{orderId}",
            produces = { "application/json" }
            )
        default ResponseEntity<OrderDto> getMyOrderById(
        @Parameter(name = "orderId", description = "The ID of the order", required = true, in = ParameterIn.PATH) @PathVariable("orderId") Long orderId
            ) {
            return getDelegate().getMyOrderById(orderId);
            }


            /**
            * GET /orders/me : Get all of my orders
                * Retrieves a list of all orders placed by the currently logged-in user
            *
            * @return Successfully retrieved orders (status code 200)
                *         or Unauthorized (status code 401)
                *         or Forbidden (status code 403)
            */
                @Operation(
                operationId = "getMyOrders",
                    summary = "Get all of my orders",
                    description = "Retrieves a list of all orders placed by the currently logged-in user",
                responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved orders", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
                },
                security = {
                    @SecurityRequirement(name = "bearerAuth")
                }
                )
                        @PreAuthorize("hasAuthority('SCOPE_order:readMe')")
            @RequestMapping(
            method = RequestMethod.GET,
            value = "/orders/me",
            produces = { "application/json" }
            )
        default ResponseEntity<List<OrderDto>> getMyOrders(
        
            ) {
            return getDelegate().getMyOrders();
            }


            /**
            * GET /orders/{orderId} : Retrieves information about a specific order
            *
                * @param orderId The ID of the order (required)
            * @return Order retrieved successfully (status code 200)
                *         or Order not found (status code 404)
                *         or Unauthorized (status code 401)
            */
                @Operation(
                operationId = "getOrderById",
                    summary = "Retrieves information about a specific order",
                responses = {
                    @ApiResponse(responseCode = "200", description = "Order retrieved successfully", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Order not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
                },
                security = {
                    @SecurityRequirement(name = "bearerAuth")
                }
                )
                        @PreAuthorize("hasAuthority('SCOPE_order:read')")
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
                * use PROCESSING, ACCEPTED, IN_DELIVERY, DONE, CANCELLED as possible statuses
            *
                * @param orderId The ID of the order (required)
                * @param orderStatus PROCESSING, ACCEPTED, IN_DELIVERY, DONE, CANCELLED (required)
            * @return Order status updated successfully (status code 200)
                *         or Invalid request (status code 400)
                *         or Order not found (status code 404)
                *         or Unauthorized (status code 401)
            */
                @Operation(
                operationId = "updateOrderStatus",
                    summary = "Updates the status of an order",
                    description = "use PROCESSING, ACCEPTED, IN_DELIVERY, DONE, CANCELLED as possible statuses",
                responses = {
                    @ApiResponse(responseCode = "200", description = "Order status updated successfully", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Order not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
                },
                security = {
                    @SecurityRequirement(name = "bearerAuth")
                }
                )
                        @PreAuthorize("hasAuthority('SCOPE_order:updateStatus')")
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
