package ua.rivnegray.boardgames_shop.model;

public enum BoardGameLanguage {
    ENGLISH("Англійська"),
    RUSSIAN("Російська"),
    UKRAINIAN("Українська"),
    POLISH("Польська"),
    GERMAN("Німецька"),
    FRENCH("Французька"),
    OTHER("Інша");

    private final String languageNameInUkrainianForFiltering;

    BoardGameLanguage(String languageNameInUkrainianForFiltering) {
        this.languageNameInUkrainianForFiltering = languageNameInUkrainianForFiltering;
    }

    public String getLanguageNameInUkrainianForFiltering() {
        return languageNameInUkrainianForFiltering;
    }

    public static BoardGameLanguage fromLanguageNameInUkrainian(String languageNameInUkrainianForFiltering) {
        for (BoardGameLanguage language : values()) {
            if (language.getLanguageNameInUkrainianForFiltering().equals(languageNameInUkrainianForFiltering)) {
                return language;
            }
        }
        throw new IllegalArgumentException("No enum constant found for languageNameInUkrainianForFiltering: " + languageNameInUkrainianForFiltering);
    }
}
