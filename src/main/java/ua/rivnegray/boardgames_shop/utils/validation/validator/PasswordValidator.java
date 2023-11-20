package ua.rivnegray.boardgames_shop.utils.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ua.rivnegray.boardgames_shop.utils.validation.ValidationConstants;
import ua.rivnegray.boardgames_shop.utils.validation.annotation.Password;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private Pattern pattern;
    @Override
    public void initialize(Password constraintAnnotation) {
        pattern = Pattern.compile(ValidationConstants.PASSWORD);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return false;

        return pattern.matcher(value).matches();
    }
}
