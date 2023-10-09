package ua.rivnegray.boardgames_shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
}
