package ua.rivnegray.boardgames_shop.utils.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ua.rivnegray.boardgames_shop.utils.validation.ValidationConstants;
import ua.rivnegray.boardgames_shop.utils.validation.annotation.NameAndSurname;

import java.util.regex.Pattern;

public class NameAndSurnameValidator implements ConstraintValidator<NameAndSurname, String> {
    private Pattern pattern;
    @Override
    public void initialize(NameAndSurname constraintAnnotation) {
        pattern = Pattern.compile(ValidationConstants.NAME_AND_SURNAME);
    }
    @Override
    public boolean isValid(String nameAndSurname, ConstraintValidatorContext constraintValidatorContext) {
        if (nameAndSurname == null)
            return false;
        return pattern.matcher(nameAndSurname).matches();
    }
}
