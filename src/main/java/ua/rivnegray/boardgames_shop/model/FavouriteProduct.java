package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteProduct extends BaseEntity{
    @ManyToOne
    private User user;

    @ManyToOne
    private BoardGame boardGame;
}
