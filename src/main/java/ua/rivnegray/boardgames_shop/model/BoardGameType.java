package ua.rivnegray.boardgames_shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameTypeNotFoundByNameException;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum BoardGameType {

    STRATEGY("Стратегії"),
    ECONOMY("Економічні"),
    MILITARY("Військові"),
    AMERITRASH("Амерiтреш"),
    EURO("Євро"),
    ADVENTURE("Пригодницькі"),
    COOPERATIVE("Кооперативні"),
    ONE_VERSUS_ALL("Один проти всіх"),
    LUCK("На удачу"),
    DICE("На кубиках"),
    AGILITY("На ловкість"),
    LOGICAL("Логічні"),
    QUIZ("Вікторини"),
    MAFIA("Мафія"),
    CITY_BUILDING("Містобудування"),
    CREATIVE("Творчі"),
    QUEST("Квести"),
    HISTORY("Історичні"),
    FANTASY("Фантастика"),
    POST_APOCALYPSE("Постапокаліпсис"),
    CIVILIZATION("Цивілізація"),
    HUMOR("Гумор"),
    PUZZLE("Головоломки"),
    SPORT("Спорт"),
    COLLECTIBLE_CARD_GAMES("Колекційні карткові ігри");

    private final String boardGameTypeNameInUkrainian;
    private static final Map<String, BoardGameType> typeNameToEnumMap = new HashMap<>();

    static{
        for(BoardGameType boardGameType : BoardGameType.values()){
            typeNameToEnumMap.put(boardGameType.getBoardGameTypeNameInUkrainian(), boardGameType);
        }
    }

    public static BoardGameType fromBoardGameTypeNameInUkrainian(String boardGameTypeNameInUkrainian) {
        BoardGameType boardGameTypeEnumValue = typeNameToEnumMap.get(boardGameTypeNameInUkrainian);
        if(boardGameTypeEnumValue == null)
            throw new BoardGameTypeNotFoundByNameException(boardGameTypeNameInUkrainian);
        return boardGameTypeEnumValue;
    }
}
