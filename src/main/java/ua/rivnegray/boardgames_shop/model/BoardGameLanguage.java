package ua.rivnegray.boardgames_shop.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ua.rivnegray.boardgames_shop.exceptions.notFoundExceptions.BoardGameLanguageNotFoundByNameException;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public enum BoardGameLanguage {
    ENGLISH("Англійська"),
    RUSSIAN("Російська"),
    UKRAINIAN("Українська"),
    POLISH("Польська"),
    GERMAN("Німецька"),
    FRENCH("Французька"),
    OTHER("Інша");

    private final String languageNameInUkrainian;
    private static final Map<String, BoardGameLanguage> languageNameInUkrainianToBoardGameLanguageMap = new HashMap<>();

    static {
        for(BoardGameLanguage boardGameLanguage : BoardGameLanguage.values()){
            languageNameInUkrainianToBoardGameLanguageMap.put(boardGameLanguage.getLanguageNameInUkrainian(), boardGameLanguage);
        }
    }

    public static BoardGameLanguage fromLanguageNameInUkrainian(String languageNameInUkrainian) {
        BoardGameLanguage boardGameLanguageEnumValue = languageNameInUkrainianToBoardGameLanguageMap.get(languageNameInUkrainian);
        if(boardGameLanguageEnumValue == null)
            throw new BoardGameLanguageNotFoundByNameException(languageNameInUkrainian);

        return boardGameLanguageEnumValue;
    }
}
