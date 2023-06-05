package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardGame extends Product {

    public BoardGame(String Manufacturer, String productName, String productDescription, BigDecimal productPrice, Integer productQuantityInStock, String productImageURL, ProductCategory productCategory, String productCode, String gameSet, Set<BoardGameGenre> gameGenres, Set<BoardGameMechanic> gameMechanics, Integer minAge, Integer minPlayers, String gameDuration, BoardGameLanguage gameLanguage, String BGGLink) {
        super(Manufacturer, productName, productDescription, productPrice, productQuantityInStock, productImageURL, productCategory);
        this.productCode = productCode;
        this.gameSet = gameSet;
        this.gameGenres = gameGenres;
        this.gameMechanics = gameMechanics;
        this.minAge = minAge;
        this.minPlayers = minPlayers;
        this.gameDuration = gameDuration;
        this.gameLanguage = gameLanguage;
        this.BGGLink = BGGLink;
    }

    /**
     *
     * <b><i>ENG:</i></b>The product's Stock Keeping Unit (SKU), an identifier provided by the manufacturer.<br>
     * <b><i>RU/UA:</i></b> Артикул
     */
    private String productCode;

    /**
     * The game set / gae configuration of a board game: <br>
     * Components of a board game, that come in the box, included in the package
     * <b><i>RU/UA:</i></b> Комплектация
     */
    private String gameSet;

    // todo figure out of game type is needed alongside with game genre and game mechanic

//    private Set<BoardGameType> gameTypes = new HashSet<>();

    @ManyToMany
    private Set<BoardGameGenre> gameGenres = new HashSet<>();

    @ManyToMany
    private Set<BoardGameMechanic> gameMechanics = new HashSet<>();

    private Integer minAge;

    private Integer minPlayers;

    private String gameDuration;

    @Enumerated(EnumType.STRING)
    private BoardGameLanguage gameLanguage;

    /**
     * <b><i>ENG:</i></b> The link to the board game's page on BoardGameGeek.com<br>
     */
    @URL
    private String BGGLink;
}
