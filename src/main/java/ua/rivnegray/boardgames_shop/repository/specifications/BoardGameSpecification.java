package ua.rivnegray.boardgames_shop.repository.specifications;

import org.springframework.data.jpa.domain.Specification;
import ua.rivnegray.boardgames_shop.model.BoardGame;
import ua.rivnegray.boardgames_shop.model.BoardGameLanguage;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class BoardGameSpecification {
//    public static Specification<BoardGame> hasAttribute(String attribute, Set<String> values) {
//        return (root, query, cb) -> {
//            if (values == null || values.isEmpty()) {
//                return null;
//            }
//            return root.get(attribute).in(values);
//        };
//    }
//
//    public static Specification<BoardGame> hasAttributes(Map<String, Set<String>> filterMap) {
//        Specification<BoardGame> specification = Specification.where(null);
//        for (Map.Entry<String, Set<String>> entry : filterMap.entrySet()) {
//            specification = specification.and(hasAttribute(entry.getKey(), entry.getValue()));
//        }
//        return specification;
//    }

    public static Specification<BoardGame> hasManufacturers(Set<String> manufacturers) {
        return (root, query, cb) -> {
            if (manufacturers == null || manufacturers.isEmpty()) {
                return null;
            }
            return root.get("manufacturer").in(manufacturers);
        };
    }

    public static Specification<BoardGame> hasPriceInRange(BigDecimal minProductPrice, BigDecimal maxProductPrice){
        return (root, query, cb) -> {
            if (minProductPrice == null && maxProductPrice == null) {
                return null;
            }
            if (minProductPrice == null) {
                return cb.lessThanOrEqualTo(root.get("productPrice"), maxProductPrice);
            }
            if (maxProductPrice == null) {
                return cb.greaterThanOrEqualTo(root.get("productPrice"), minProductPrice);
            }
            return cb.between(root.get("productPrice"), minProductPrice, maxProductPrice);
        };
    }

    public static Specification<BoardGame> hasBoardGameGenres(Set<String> boardGameGenres) {
        return (root, query, cb) -> {
            if (boardGameGenres == null || boardGameGenres.isEmpty()) {
                return null;
            }
            return root.get("boardGameGenre").in(boardGameGenres);
        };
    }

    public static Specification<BoardGame> hasBoardGameMechanics(Set<String> boardGameMechanics) {
        return (root, query, cb) -> {
            if (boardGameMechanics == null || boardGameMechanics.isEmpty()) {
                return null;
            }
            return root.get("boardGameMechanic").in(boardGameMechanics);
        };
    }

    public static Specification<BoardGame> hasMinAges(Set<Integer> minAges){
        return (root, query, cb) -> {
            if (minAges == null || minAges.isEmpty()) {
                return null;
            }
            return root.get("minAge").in(minAges);
        };
    }

    public static Specification<BoardGame> hasPlayersInRange(Integer minPlayers, Integer maxPlayers){
        return (root, query, cb) -> {
            if (minPlayers == null && maxPlayers == null) {
                return null;
            }
            if (minPlayers == null) {
                return cb.lessThanOrEqualTo(root.get("minPlayers"), maxPlayers);
            }
            if (maxPlayers == null) {
                return cb.greaterThanOrEqualTo(root.get("minPlayers"), minPlayers);
            }
            return cb.between(root.get("minPlayers"), minPlayers, maxPlayers);
        };
    }

    public static Specification<BoardGame> hasLanguage(Set<BoardGameLanguage> boardGameLanguages){
        return (root, query, cb) -> {
            if (boardGameLanguages == null || boardGameLanguages.isEmpty()) {
                return null;
            }
            return root.get("gameLanguage").in(boardGameLanguages);
        };
    }
}
