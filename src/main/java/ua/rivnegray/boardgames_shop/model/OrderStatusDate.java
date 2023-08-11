package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class OrderStatusDate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(updatable = false, nullable = false)
    private LocalDateTime date;

    public OrderStatusDate(OrderStatus status, LocalDateTime date) {
        this.status = status;
        this.date = date;
    }

    public String toStringForExcel(){
        return status.toString() + ": " + date.toString();
    }
}
