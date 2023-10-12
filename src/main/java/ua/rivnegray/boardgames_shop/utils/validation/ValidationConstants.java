package ua.rivnegray.boardgames_shop.utils.validation;

public final class ValidationConstants {
    public static final String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String phoneRegex = "^\\+([0-9]{1,4})[-.\\s]?(?:[(](\\d{1,3})[)][-.\\s]?)?(\\d{1,4})[-.\\s]?(\\d{1,4})[-.\\s]?(\\d{1,9})$\n";
    public static final String firstNameAndLastNameRegex = "^[A-Z][a-zA-Z]+( [a-zA-Z]+)*$";
    public static final String passwordRegex = "^(?=.*[A-Z])(?=.*[\\d!@#$%^&*()-_=+{}[\\]\\\\|;:'\",.<>?/]).{8,32}$\n";

    private ValidationConstants() {
    }
}
