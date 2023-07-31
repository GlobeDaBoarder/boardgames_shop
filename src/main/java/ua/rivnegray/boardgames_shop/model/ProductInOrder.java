package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductInOrder extends BaseEntity{
    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;

    private Integer quantity;

    public BigDecimal calculateTotalPrice(){
        return product.getProductPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
