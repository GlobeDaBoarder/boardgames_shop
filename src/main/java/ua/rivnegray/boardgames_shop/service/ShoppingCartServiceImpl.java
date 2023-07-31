package ua.rivnegray.boardgames_shop.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.AddProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateUserProfileDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.MapShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateQuantityOfProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.ShoppingCartDto;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.AddressIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.AddressNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.ShoppingCartIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.OrderMapper;
import ua.rivnegray.boardgames_shop.mapper.ProductMapper;
import ua.rivnegray.boardgames_shop.mapper.ShoppingCartMapper;
import ua.rivnegray.boardgames_shop.mapper.UserMapper;
import ua.rivnegray.boardgames_shop.model.Address;
import ua.rivnegray.boardgames_shop.model.Order;
import ua.rivnegray.boardgames_shop.model.OrderStatus;
import ua.rivnegray.boardgames_shop.model.PaymentStatus;
import ua.rivnegray.boardgames_shop.model.ProductInShoppingCart;
import ua.rivnegray.boardgames_shop.model.ShoppingCart;
import ua.rivnegray.boardgames_shop.model.UserProfile;
import ua.rivnegray.boardgames_shop.repository.AddressRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;
import ua.rivnegray.boardgames_shop.repository.OrderRepository;
import ua.rivnegray.boardgames_shop.repository.ProductInShoppingCartRepository;
import ua.rivnegray.boardgames_shop.repository.ShoppingCartRepository;
import ua.rivnegray.boardgames_shop.repository.UserProfileRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    ShoppingCartRepository shoppingCartRepository;
    ProductInShoppingCartRepository productInShoppingCartRepository;
    ShoppingCartMapper shoppingCartMapper;
    ProductMapper productMapper;
    BoardGameRepository boardGameRepository;

    OrderRepository orderRepository;

    UserMapper userMapper;

    OrderMapper orderMapper;

    AddressRepository addressRepository;

    EntityManager entityManager;

    UserProfileRepository userProfileRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ShoppingCartMapper shoppingCartMapper,
                                   ProductInShoppingCartRepository productInShoppingCartRepository,
                                   ProductMapper productMapper,
                                   BoardGameRepository boardGameRepository,
                                   OrderRepository orderRepository,
                                   OrderMapper orderMapper,
                                   AddressRepository addressRepository, EntityManager entityManager,
                                   UserMapper userMapper, UserProfileRepository userProfileRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartMapper = shoppingCartMapper;
        this.productInShoppingCartRepository = productInShoppingCartRepository;
        this.productMapper = productMapper;
        this.boardGameRepository = boardGameRepository;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.addressRepository = addressRepository;
        this.entityManager = entityManager;
        this.userMapper = userMapper;
        this.userProfileRepository = userProfileRepository;
    }

//    private ShoppingCart fetchShoppingCartFromRepo(Long cartId) {
//        return this.shoppingCartRepository.findById(cartId)
//                .orElseThrow(() -> new ShoppingCartIdNotFoundException(cartId));
//    }
//
    // admin operations

    @Override
    public List<ProductInShoppingCartDto> getProductsInShoppingCart(Long cartId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ShoppingCartIdNotFoundException(cartId));
        return shoppingCart.getProductsInShoppingCart().stream()
                .map(productInShoppingCart -> this.productMapper.toProductInShoppingCartDto(productInShoppingCart))
                .toList();
    }

    // current user operations
    private ShoppingCart getShoppingCartOfCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
        String username = jwtPrincipal.getSubject();
        Long id = jwtPrincipal.getClaim("id");

        return this.shoppingCartRepository.findById(id)
                .orElseThrow(() -> new ShoppingCartIdNotFoundException(id));
    }

    @Override
    public ShoppingCartDto clearMyShoppingCart() {
        ShoppingCart shoppingCart = getShoppingCartOfCurrentUser();
        shoppingCart.getProductsInShoppingCart().clear();
        this.shoppingCartRepository.save(shoppingCart);
        return this.shoppingCartMapper.toShoppingCartDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto addProductToMyShoppingCart(Long productId) {
        ShoppingCart shoppingCart = getShoppingCartOfCurrentUser();

        Optional<ProductInShoppingCart> existingProductInCart = shoppingCart.getProductsInShoppingCart().stream()
                .filter(p -> p.getProduct().getId().equals(productId))
                .findFirst();

        if (existingProductInCart.isPresent()) {
            existingProductInCart.get().setQuantity(existingProductInCart.get().getQuantity() + 1);
        } else {
            ProductInShoppingCart productInShoppingCart = new ProductInShoppingCart();
            // tod change from boardgame repository to general product repository later
            productInShoppingCart.setProduct(this.boardGameRepository.findById(productId)
                    .orElseThrow(() -> new BoardGameIdNotFoundException(productId)));
            productInShoppingCart.setShoppingCart(shoppingCart);
            productInShoppingCart.setQuantity(1);

            shoppingCart.getProductsInShoppingCart().add(productInShoppingCart);
        }

        this.shoppingCartRepository.save(shoppingCart);
        return this.shoppingCartMapper.toShoppingCartDto(shoppingCart);
    }


    @Override
    public List<ProductInShoppingCartDto> getProductsInMyShoppingCart() {
        ShoppingCart shoppingCart = getShoppingCartOfCurrentUser();
        return shoppingCart.getProductsInShoppingCart().stream()
                .map(productInShoppingCart -> this.productMapper.toProductInShoppingCartDto(productInShoppingCart))
                .toList();
    }


    @Override
    public ShoppingCartDto removeProductFromMyShoppingCart(Long productInCartId) {
        ShoppingCart shoppingCart = getShoppingCartOfCurrentUser();
        Set<ProductInShoppingCart> productsInShoppingCart = shoppingCart.getProductsInShoppingCart();
        productsInShoppingCart.removeIf(productInShoppingCart -> productInShoppingCart.getId() == productInCartId);
        this.shoppingCartRepository.save(shoppingCart);
        return this.shoppingCartMapper.toShoppingCartDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto updateQuantityOfProductInMyShoppingCart(Long productInCartId,
                                   UpdateQuantityOfProductInShoppingCartDto updateQuantityOfProductInShoppingCartDto) {
        ProductInShoppingCart productInShoppingCart = this.productInShoppingCartRepository.findById(productInCartId)
                .orElseThrow(() -> new ShoppingCartIdNotFoundException(productInCartId));
        productInShoppingCart.setQuantity(updateQuantityOfProductInShoppingCartDto.quantity());
        this.productInShoppingCartRepository.save(productInShoppingCart);
        return this.shoppingCartMapper.toShoppingCartDto(getShoppingCartOfCurrentUser());
    }
    @Override
    public OrderDto checkoutMyUser(Long addressId) {
        ShoppingCart shoppingCart = getShoppingCartOfCurrentUser();

        Order newOrder = Order.builder()
                .userProfile(shoppingCart.getUserProfile())
                .orderItems(shoppingCart.getProductsInShoppingCart().stream()
                        .map(productInShoppingCart -> this.productMapper.toProductInOrder(productInShoppingCart))
                        .collect(Collectors.toSet()))
                .status(OrderStatus.PLACED)
                .orderDate(LocalDateTime.now())
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
                .dateOrderPlaced(LocalDateTime.now())
                .paymentStatus(PaymentStatus.UNPAID)
                .build();

        Order finalNewOrder = newOrder;
        newOrder.getOrderItems().forEach(orderItem -> orderItem.setOrder(finalNewOrder));

        newOrder = this.orderRepository.save(newOrder);

        this.entityManager.flush();
        this.entityManager.refresh(newOrder);

        clearMyShoppingCart();

        return this.orderMapper.orderToOrderDto(newOrder);
    }
}
