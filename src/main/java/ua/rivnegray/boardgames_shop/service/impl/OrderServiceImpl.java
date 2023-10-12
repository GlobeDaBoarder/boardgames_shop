package ua.rivnegray.boardgames_shop.service.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateOrderDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.UserInfoForOrderDto;
import ua.rivnegray.boardgames_shop.DTO.response.OrderDto;
import ua.rivnegray.boardgames_shop.exceptions.internalServerExceptions.ExcelDataExportException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameIdNotFoundException;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.OrderIdNotFoundException;
import ua.rivnegray.boardgames_shop.mapper.OrderMapper;
import ua.rivnegray.boardgames_shop.mapper.UserMapper;
import ua.rivnegray.boardgames_shop.model.Address;
import ua.rivnegray.boardgames_shop.model.Order;
import ua.rivnegray.boardgames_shop.model.OrderStatus;
import ua.rivnegray.boardgames_shop.model.OrderStatusDate;
import ua.rivnegray.boardgames_shop.model.PaymentStatus;
import ua.rivnegray.boardgames_shop.model.ProductInOrder;
import ua.rivnegray.boardgames_shop.model.User;
import ua.rivnegray.boardgames_shop.model.UserRegistrationStatus;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;
import ua.rivnegray.boardgames_shop.repository.OrderRepository;
import ua.rivnegray.boardgames_shop.repository.UserRepository;
import ua.rivnegray.boardgames_shop.service.OrderService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final String[] EXCEL_HEADERS = {
        "Order ID", // 0
                "Customer First Name",  // 1
                "Customer Last Name", // 2
                "Customer Email", // 3
                "Order Items", // 4
                "Current Status", // 5
                "Total Price", // 6
                "Address", // 7
                "Payment Status", // 8
                "OrderStatusHistory" // 9
    };


    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final BoardGameRepository boardGameRepository;
    private final EntityManager entityManager;


    // admin orders operations
    @Override
    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        User user = findOrCreateUserProfile(createOrderDto.userProfileDto());

        Address address = userMapper.toAddress(createOrderDto.addAndUpdateAddressDto());
        if(user.getAddresses().stream().noneMatch(address::equals)){
            user.getAddresses().add(address);
        }

        this.userRepository.save(user);

        Set<ProductInOrder> productInOrders = createOrderDto.mapShoppingCartDto().simpleProductInShoppingCartDtos().stream()
                .map(simpleProductInShoppingCartDto -> ProductInOrder.builder()
                        .product(this.boardGameRepository.findById(simpleProductInShoppingCartDto.productId())
                                .orElseThrow(() -> new BoardGameIdNotFoundException(simpleProductInShoppingCartDto.productId())))
                        .quantity(simpleProductInShoppingCartDto.quantity())
                        .build()
                )
                .collect(Collectors.toSet());

        Order order = Order.builder()
                .user(user)
                .orderItems(productInOrders)
                .totalPrice(
                        productInOrders.stream()
                                .map(ProductInOrder::calculateTotalPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                )
                .currentStatus(OrderStatus.PLACED)
                .address(address)
                .paymentStatus(PaymentStatus.UNPAID)
                .build();

        order.getOrderItems().forEach(productInOrder -> productInOrder.setOrder(order));

        this.orderRepository.save(order);

        entityManager.flush();
        entityManager.refresh(order);

        return this.orderMapper.orderToOrderDto(order);
    }

    private User findOrCreateUserProfile(UserInfoForOrderDto userInfoForOrderDto) {
        Optional<User> userFoundByEmail = this.userRepository.findByEmail(userInfoForOrderDto.email());
        if(userFoundByEmail.isPresent()){
            User existingProfile = userFoundByEmail.get();
            // if the user is registered already, don't update it's information. Only update if it's not registered
            if(existingProfile.getRegistrationStatus().equals(UserRegistrationStatus.NOT_REGISTERED)){
                existingProfile.setPhone(userInfoForOrderDto.phone());
                existingProfile.setFirstName(userInfoForOrderDto.firstName());
                existingProfile.setLastName(userInfoForOrderDto.lastName());
            }

            return existingProfile;
        }

        return User.builder()
                .email(userInfoForOrderDto.email())
                .phone(userInfoForOrderDto.phone())
                .firstName(userInfoForOrderDto.firstName())
                .lastName(userInfoForOrderDto.lastName())
                .registrationStatus(UserRegistrationStatus.NOT_REGISTERED)
                .build();
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return this.orderRepository.findAll().stream()
                .map(this.orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        return this.orderRepository.findById(orderId)
                .map(this.orderMapper::orderToOrderDto)
                .orElseThrow(() -> new OrderIdNotFoundException(orderId));
    }

    @Override
    public OrderDto updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        Order order = fetchOrderById(orderId);
        order.setCurrentStatus(orderStatus);
        this.orderRepository.save(order);
        return this.orderMapper.orderToOrderDto(order);
    }

    // my order operations
    @Override
    public void cancelMyOrder(Long orderId) {
        Order orderToCancel = this.orderRepository.findByIdAndUser_Id(orderId, getCurrentUserId())
                        .orElseThrow(() -> new OrderIdNotFoundException(orderId));
        orderToCancel.setCurrentStatus(OrderStatus.CANCELLED);
        this.orderRepository.save(orderToCancel);
    }

    @Override
    public List<OrderDto> getMyOrders() {
        return this.getCurrentUserOrders().stream()
                .map(this.orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getMyOrderById(Long orderId) {
        return this.orderRepository.findByIdAndUser_Id(orderId, getCurrentUserId())
                .map(this.orderMapper::orderToOrderDto)
                .orElseThrow(() -> new OrderIdNotFoundException(orderId));
    }

    private Order fetchOrderById(Long orderId) {
        return this.orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderIdNotFoundException(orderId));
    }

    private Long getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
        return jwtPrincipal.getClaim("id");
    }

    private List<Order> getCurrentUserOrders(){
        return this.orderRepository.findAllByUser_Id(getCurrentUserId());
    }

    @Override
    public byte[] exportOrdersToExcel(LocalDate startDate, LocalDate endDate){
        List<Order> orders = getOrdersWherePlacedStatusBetween(startDate, endDate);

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Orders");

            createHeaders(workbook, sheet);

            fillData(orders, workbook, sheet);

            // Auto-size columns
            for (int i = 0; i < EXCEL_HEADERS.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Convert workbook to byte array
            workbook.write(bos);

            return bos.toByteArray();
        } catch (IOException e) {
            throw new ExcelDataExportException(e);
        }
    }

    private static void fillData(List<Order> orders, Workbook workbook, Sheet sheet) {
        CellStyle wrapTextCellStyle = workbook.createCellStyle();
        wrapTextCellStyle.setWrapText(true);

        int rowNum = 1;
        for (Order order : orders) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(order.getId());
            row.createCell(1).setCellValue(order.getUser().getFirstName());
            row.createCell(2).setCellValue(order.getUser().getLastName());
            row.createCell(3).setCellValue(order.getUser().getEmail());

            Cell orderItensCell = row.createCell(4);
            orderItensCell.setCellValue(order.getOrderItems().stream()
                    .map(ProductInOrder::toStringForExcel)
                    .collect(Collectors.joining("\r\n")));
            orderItensCell.setCellStyle(wrapTextCellStyle);

            row.createCell(5).setCellValue(order.getCurrentStatus().name());
            row.createCell(6).setCellValue(order.getTotalPrice().doubleValue());
            row.createCell(7).setCellValue(order.getAddress().toStringForExcel());
            row.createCell(8).setCellValue(order.getPaymentStatus().name());


            Cell orderHistoryCell = row.createCell(9);
            orderHistoryCell.setCellValue(order.getOrderStatusHistory().stream()
                    .map(OrderStatusDate::toStringForExcel)
                    .collect(Collectors.joining("\r\n")));
            orderHistoryCell.setCellStyle(wrapTextCellStyle);
        }
    }

    private static void createHeaders(Workbook workbook, Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);


        for (int i = 0; i < EXCEL_HEADERS.length; i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(EXCEL_HEADERS[i]);
            headerCell.setCellStyle(headerStyle);
        }
    }

    private List<Order> getOrdersWherePlacedStatusBetween(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findAllByOrderStatusHistory_StatusAndOrderStatusHistory_DateBetween(OrderStatus.PLACED,
                LocalDateTime.of(startDate, LocalTime.MIN),
                LocalDateTime.of(endDate, LocalTime.MIN));
    }

}
