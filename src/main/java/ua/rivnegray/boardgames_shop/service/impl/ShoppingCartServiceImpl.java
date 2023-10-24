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
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.AddressIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.ShoppingCartIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.OrderMapper;
import ua.rivnegray.boardgames_shop.mapper.ProductMapper;
import ua.rivnegray.boardgames_shop.model.Order;
import ua.rivnegray.boardgames_shop.model.OrderStatus;
import ua.rivnegray.boardgames_shop.model.PaymentStatus;
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

        Optional<ProductInShoppingCart> existingProductInCart = shoppingCart.getProductsInShoppingCart().stream()
                .filter(p -> p.getProduct().getId().equals(productId))
                .findFirst();

        if (existingProductInCart.isPresent()) {
            existingProductInCart.get().setQuantity(existingProductInCart.get().getQuantity() + 1);
        } else {
            ProductInShoppingCart productInShoppingCart = new ProductInShoppingCart();
            // todo  change from boardgame repository to general product repository later
            productInShoppingCart.setProduct(this.boardGameRepository.findById(productId)
                    .orElseThrow(() -> new BoardGameIdNotFoundException(productId)));
            productInShoppingCart.setShoppingCart(shoppingCart);
            productInShoppingCart.setQuantity(1);

            shoppingCart.getProductsInShoppingCart().add(productInShoppingCart);
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
                .orElseThrow(() -> new ShoppingCartIdNotFoundException(productInCartId));
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
                            ProductInOrder productInOrder= ProductInOrder.builder()
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
    public List<ProductInShoppingCartDto> mapCart(List<MapProductInCartCartDto> mapShoppingCartDto) {
       ShoppingCart currentUserShoppingCart = getShoppingCartOfCurrentUser();

       currentUserShoppingCart.getProductsInShoppingCart().addAll(mapShoppingCartDto.stream()
                .map(simpleProductInShoppingCartDto -> ProductInShoppingCart.builder()
                        .product(this.boardGameRepository.findById(simpleProductInShoppingCartDto.productId())
                                .orElseThrow(() -> new BoardGameIdNotFoundException(simpleProductInShoppingCartDto.productId())))
                        .shoppingCart(currentUserShoppingCart)
                        .quantity(simpleProductInShoppingCartDto.quantity())
                        .build()
                )
                .collect(Collectors.toSet())
        );

       this.shoppingCartRepository.save(currentUserShoppingCart);

       return currentUserShoppingCart.getProductsInShoppingCart().stream()
               .map(this.productMapper::toProductInShoppingCartDto)
               .toList();
    }


    private ShoppingCart getShoppingCartOfCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
        Long id = jwtPrincipal.getClaim("id");

        return this.shoppingCartRepository.findById(id)
                .orElseThrow(() -> new ShoppingCartIdNotFoundException(id));
    }
}
