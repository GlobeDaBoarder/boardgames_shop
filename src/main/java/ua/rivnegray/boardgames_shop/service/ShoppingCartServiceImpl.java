package ua.rivnegray.boardgames_shop.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.AddProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.request.update.UpdateQuantityOfProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.ProductInShoppingCartDto;
import ua.rivnegray.boardgames_shop.DTO.response.ShoppingCartDto;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.AddressIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.AddressNotFoundException;
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

    private ShoppingCart fetchShoppingCartFromRepo(Long cartId) {
        return this.shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ShoppingCartIdNotFoundException(cartId));
    }

    @Override
    public ShoppingCartDto getShoppingCart(Long cartId) {
        return this.shoppingCartRepository.findById(cartId)
                .map(shoppingCart -> this.shoppingCartMapper.toShoppingCartDto(shoppingCart))
                .orElseThrow(() -> new ShoppingCartIdNotFoundException(cartId));
    }

    @Override
    public ShoppingCartDto clearShoppingCart(Long cartId) {
        ShoppingCart shoppingCart = fetchShoppingCartFromRepo(cartId);
        shoppingCart.getProductsInShoppingCart().clear();
        this.shoppingCartRepository.save(shoppingCart);
        return this.shoppingCartMapper.toShoppingCartDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto addProductToShoppingCart(Long cartId, AddProductInShoppingCartDto addProductInShoppingCartDto) {
        ProductInShoppingCart productInShoppingCart = this.productMapper.toProductInShoppingCart(cartId,
                addProductInShoppingCartDto, this.boardGameRepository, this.shoppingCartRepository);
        ShoppingCart shoppingCart = fetchShoppingCartFromRepo(cartId);
        shoppingCart.getProductsInShoppingCart().add(productInShoppingCart);
        this.shoppingCartRepository.save(shoppingCart);
        return this.shoppingCartMapper.toShoppingCartDto(shoppingCart);
    }

    @Override
    public List<ProductInShoppingCartDto> getProductsInShoppingCart(Long cartId) {
        ShoppingCart shoppingCart = fetchShoppingCartFromRepo(cartId);
        return shoppingCart.getProductsInShoppingCart().stream()
                .map(productInShoppingCart -> this.productMapper.toProductInShoppingCartDto(productInShoppingCart))
                .toList();
    }

    @Override
    public ShoppingCartDto removeProductFromShoppingCart(Long cartId, Long productInCartId) {
        ShoppingCart shoppingCart = fetchShoppingCartFromRepo(cartId);
        Set<ProductInShoppingCart> productsInShoppingCart = shoppingCart.getProductsInShoppingCart();
        productsInShoppingCart.removeIf(productInShoppingCart -> productInShoppingCart.getId() == productInCartId);
        this.shoppingCartRepository.save(shoppingCart);
        return this.shoppingCartMapper.toShoppingCartDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto updateQuantityOfProductInShoppingCart(Long cartId, Long productInCartId, UpdateQuantityOfProductInShoppingCartDto updateQuantityOfProductInShoppingCartDto) {
        ProductInShoppingCart productInShoppingCart = this.productInShoppingCartRepository.findById(productInCartId)
                .orElseThrow(() -> new ShoppingCartIdNotFoundException(productInCartId));
        productInShoppingCart.setQuantity(updateQuantityOfProductInShoppingCartDto.quantity());
        this.productInShoppingCartRepository.save(productInShoppingCart);
        return getShoppingCart(cartId);
    }

    @Override
    public OrderDto checkoutUnregisteredUser(Long cartId, AddAndUpdateAddressDto addressDto) {
        ShoppingCart shoppingCart = fetchShoppingCartFromRepo(cartId);

        Address unregeisteredUserAddress = this.userMapper.toAddress(addressDto);

        //todo unregistered user logic. how does shopping cart for it exist?
        UserProfile unregeisteredUserProfile = shoppingCart.getUserProfile();
        unregeisteredUserProfile.getAddresses().add(unregeisteredUserAddress);

        unregeisteredUserProfile = this.userProfileRepository.save(unregeisteredUserProfile);

        this.entityManager.flush();
        this.entityManager.refresh(unregeisteredUserProfile);

        Order newOrder = Order.builder()
                .userProfile(unregeisteredUserProfile)
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
                .address(
                        unregeisteredUserProfile.getAddresses().stream()
                                .max(Comparator.comparing(Address::getDateCreated))
                                .orElseThrow(() -> new AddressNotFoundException("No address found for user"))
                )
                .dateOrderPlaced(LocalDateTime.now())
                .paymentStatus(PaymentStatus.UNPAID)
                .build();


        Order finalNewOrder = newOrder;
        newOrder.getOrderItems().forEach(orderItem -> orderItem.setOrder(finalNewOrder));

        newOrder = this.orderRepository.save(newOrder);

        this.entityManager.flush();
        this.entityManager.refresh(newOrder);

        clearShoppingCart(cartId);

        return this.orderMapper.orderToOrderDto(newOrder);
    }

    @Override
    public OrderDto checkoutRegisteredUser(Long cartId, Long addressId) {
        ShoppingCart shoppingCart = fetchShoppingCartFromRepo(cartId);

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

        clearShoppingCart(cartId);

        return this.orderMapper.orderToOrderDto(newOrder);
    }
}
