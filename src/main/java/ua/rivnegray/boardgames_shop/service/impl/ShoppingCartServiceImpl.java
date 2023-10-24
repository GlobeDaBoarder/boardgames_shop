package ua.rivnegray.boardgames_shop.service.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.create.MapProductInCartCartDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateQuantityOfProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.exceptions.badRequestExceptions.InsufficientStockException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.AddressIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.ProductInCartIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.ShoppingCartIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.OrderMapper;
import ua.rivnegray.boardgames_shop.mapper.ProductMapper;
import ua.rivnegray.boardgames_shop.model.Order;
import ua.rivnegray.boardgames_shop.model.OrderStatus;
import ua.rivnegray.boardgames_shop.model.PaymentStatus;
import ua.rivnegray.boardgames_shop.model.Product;
import ua.rivnegray.boardgames_shop.model.ProductInOrder;
import ua.rivnegray.boardgames_shop.model.ProductInShoppingCart;
import ua.rivnegray.boardgames_shop.model.ShoppingCart;
import ua.rivnegray.boardgames_shop.repository.AddressRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;
import ua.rivnegray.boardgames_shop.repository.OrderRepository;
import ua.rivnegray.boardgames_shop.repository.ProductInOrderRepository;
import ua.rivnegray.boardgames_shop.repository.ProductInShoppingCartRepository;
import ua.rivnegray.boardgames_shop.repository.ShoppingCartRepository;
import ua.rivnegray.boardgames_shop.service.ShoppingCartService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductInShoppingCartRepository productInShoppingCartRepository;
    private final ProductInOrderRepository productInOrderRepository;
    private final ProductMapper productMapper;
    private final BoardGameRepository boardGameRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final AddressRepository addressRepository;
    private final EntityManager entityManager;

    /**
     * Validates if requested quantity is in stock
     *
     * @param requestedQuantity - quantity requested by user
     * @param quantityInStock   - quantity in stock
     * @param productId         - id of product
     * @throws InsufficientStockException if requested quantity is greater than quantity in stock
     **/
    private static void validateIfRequestedQuantityIsInStock(Long productId, Integer requestedQuantity, Integer quantityInStock) {
        if (requestedQuantity > quantityInStock)
            throw new InsufficientStockException(productId, requestedQuantity, quantityInStock);
    }

    /**
     * Validates if requested quantity is in stock
     *
     * @param requestedQuantity - quantity requested by user
     * @param quantityInStock   - quantity in stock
     * @throws InsufficientStockException if requested quantity is greater than quantity in stock
     **/
    private static void validateIfRequestedQuantityIsInStock(Integer requestedQuantity, Integer quantityInStock) {
        if (requestedQuantity > quantityInStock)
            throw new InsufficientStockException(requestedQuantity, quantityInStock);
    }

    // admin operations
    @Override
    public List<ProductInShoppingCartDto> getProductsInShoppingCart(Long cartId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ShoppingCartIdNotFoundException(cartId));
        return shoppingCart.getProductsInShoppingCart().stream()
                .map(this.productMapper::toProductInShoppingCartDto)
                .toList();
    }

    // current user operations
    @Override
    public void clearMyShoppingCart() {
        ShoppingCart shoppingCart = getShoppingCartOfCurrentUser();
        shoppingCart.getProductsInShoppingCart().clear();
        this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public List<ProductInShoppingCartDto> addProductToMyShoppingCart(Long productId) {
        ShoppingCart shoppingCart = getShoppingCartOfCurrentUser();

        Optional<ProductInShoppingCart> existingProductInCartOptional = shoppingCart.getProductsInShoppingCart().stream()
                .filter(p -> p.getProduct().getId().equals(productId))
                .findFirst();

        if (existingProductInCartOptional.isPresent()) {
            ProductInShoppingCart existingProductInCart = existingProductInCartOptional.get();

            validateIfRequestedQuantityIsInStock(existingProductInCart.getQuantity() + 1,
                    existingProductInCart.getProduct().getProductQuantityInStock());

            existingProductInCart.setQuantity(existingProductInCartOptional.get().getQuantity() + 1);
        } else {
            ProductInShoppingCart newProductInShoppingCart = new ProductInShoppingCart();

            validateIfRequestedQuantityIsInStock(1,
                    newProductInShoppingCart.getProduct().getProductQuantityInStock());

            newProductInShoppingCart.setProduct(this.boardGameRepository.findById(productId)
                    .orElseThrow(() -> new BoardGameIdNotFoundException(productId)));
            newProductInShoppingCart.setShoppingCart(shoppingCart);
            newProductInShoppingCart.setQuantity(1);

            shoppingCart.getProductsInShoppingCart().add(newProductInShoppingCart);
        }

        this.shoppingCartRepository.save(shoppingCart);

        return shoppingCart.getProductsInShoppingCart().stream()
                .map(this.productMapper::toProductInShoppingCartDto)
                .toList();
    }

    @Override
    public List<ProductInShoppingCartDto> getProductsInMyShoppingCart() {
        ShoppingCart shoppingCart = getShoppingCartOfCurrentUser();
        return shoppingCart.getProductsInShoppingCart().stream()
                .map(this.productMapper::toProductInShoppingCartDto)
                .toList();
    }

    @Override
    public List<ProductInShoppingCartDto> removeProductFromMyShoppingCart(Long productInCartId) {
        ShoppingCart shoppingCart = getShoppingCartOfCurrentUser();
        Set<ProductInShoppingCart> productsInShoppingCart = shoppingCart.getProductsInShoppingCart();
        productsInShoppingCart.removeIf(productInShoppingCart -> productInShoppingCart.getId().equals(productInCartId));

        this.shoppingCartRepository.save(shoppingCart);

        return shoppingCart.getProductsInShoppingCart().stream()
                .map(this.productMapper::toProductInShoppingCartDto)
                .toList();
    }

    @Override
    public List<ProductInShoppingCartDto> updateQuantityOfProductInMyShoppingCart(Long productInCartId,
                                                                                  UpdateQuantityOfProductInShoppingCartDto updateQuantityOfProductInShoppingCartDto) {
        ProductInShoppingCart productInShoppingCart = this.productInShoppingCartRepository.findById(productInCartId)
                .orElseThrow(() -> new ProductInCartIdNotFoundException(productInCartId));

        Integer quantityInStock = productInShoppingCart.getProduct().getProductQuantityInStock();
        validateIfRequestedQuantityIsInStock(updateQuantityOfProductInShoppingCartDto.quantity(), quantityInStock);

        productInShoppingCart.setQuantity(updateQuantityOfProductInShoppingCartDto.quantity());

        this.productInShoppingCartRepository.save(productInShoppingCart);

        return getShoppingCartOfCurrentUser().getProductsInShoppingCart().stream()
                .map(this.productMapper::toProductInShoppingCartDto)
                .toList();
    }

    @Override
    public OrderDto checkoutMyUser(Long addressId) {
        ShoppingCart shoppingCart = getShoppingCartOfCurrentUser();

        Order newOrder = Order.builder()
                .user(shoppingCart.getUser())
                .orderItems(shoppingCart.getProductsInShoppingCart().stream()
                        .map(productInShoppingCart -> {
                            ProductInOrder productInOrder = ProductInOrder.builder()
                                    .product(productInShoppingCart.getProduct())
                                    .quantity(productInShoppingCart.getQuantity())
                                    .build();
                            return this.productInOrderRepository.save(productInOrder);
                        })
                        .collect(Collectors.toSet()))
                .currentStatus(OrderStatus.PLACED)
                .totalPrice(
                        shoppingCart.getProductsInShoppingCart().stream()
                                .map(
                                        productInShoppingCart -> productInShoppingCart.getProduct().getProductPrice()
                                                .multiply(BigDecimal.valueOf(productInShoppingCart.getQuantity()))
                                )
                                .reduce(BigDecimal::add)
                                .orElse(BigDecimal.ZERO)
                )
                .address(this.addressRepository.findById(addressId)
                        .orElseThrow(() -> new AddressIdNotFoundException(addressId)))
                .paymentStatus(PaymentStatus.UNPAID)
                .build();

        Order finalNewOrder = newOrder;
        newOrder.getOrderItems().forEach(orderItem -> orderItem.setOrder(finalNewOrder));

        newOrder = this.orderRepository.save(newOrder);

        this.entityManager.flush();
        this.entityManager.refresh(newOrder);

        // todo change transactional propagation??
        clearMyShoppingCart();

        return this.orderMapper.orderToOrderDto(newOrder);
    }

    @Override
    public List<ProductInShoppingCartDto> mapCart(List<MapProductInCartCartDto> mapProductInCartCartDtos) {
        ShoppingCart currentUserShoppingCart = getShoppingCartOfCurrentUser();
        List<ProductInShoppingCart> newProductsInCart = new ArrayList<>();

        for (MapProductInCartCartDto mapProductInCartCartDto : mapProductInCartCartDtos) {
            Product product = boardGameRepository.findById(mapProductInCartCartDto.productId())
                    .orElseThrow(() -> new BoardGameIdNotFoundException(mapProductInCartCartDto.productId()));

            ProductInShoppingCart newProductInCart = ProductInShoppingCart.builder()
                    .product(product)
                    .shoppingCart(currentUserShoppingCart)
                    .quantity(mapProductInCartCartDto.quantity())
                    .build();

            validateIfRequestedQuantityIsInStock(mapProductInCartCartDto.productId(), newProductInCart.getQuantity(), product.getProductQuantityInStock());

            Optional<ProductInShoppingCart> potentiallyAlreadyExistingProductInCart = findMatchingProductInCart(product);
            if (potentiallyAlreadyExistingProductInCart.isPresent()) {
                ProductInShoppingCart existingProductInCart = potentiallyAlreadyExistingProductInCart.get();
                existingProductInCart.setQuantity(newProductInCart.getQuantity());

                continue;
            }

            newProductsInCart.add(newProductInCart);
        }

        currentUserShoppingCart.getProductsInShoppingCart().addAll(newProductsInCart);

        shoppingCartRepository.save(currentUserShoppingCart);

        return currentUserShoppingCart.getProductsInShoppingCart().stream()
                .map(this.productMapper::toProductInShoppingCartDto)
                .toList();
    }

    private Optional<ProductInShoppingCart> findMatchingProductInCart(Product product) {
        return getShoppingCartOfCurrentUser().getProductsInShoppingCart().stream()
                .filter(productInShoppingCart -> productInShoppingCart.getProduct().equals(product))
                .findFirst();
    }

    /**
     * Returns shopping cart of current user from security context.
     * Basically gets it from JWT token info (id)
     *
     * @return shopping cart of current user
     */
    private ShoppingCart getShoppingCartOfCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
        Long id = jwtPrincipal.getClaim("id");

        return this.shoppingCartRepository.findById(id)
                .orElseThrow(() -> new ShoppingCartIdNotFoundException(id));
    }
}
