package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user_order")
public class Order extends BaseEntity{
    @ManyToOne
    private UserProfile userProfile;

    @OneToMany(mappedBy = "order")
    private Set<ProductInOrder> orderItems = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;

    private BigDecimal totalPrice;

    @ManyToOne
    private Address address;

    private LocalDateTime dateOrderPlaced;

    private LocalDateTime dateOrderDelivered;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
