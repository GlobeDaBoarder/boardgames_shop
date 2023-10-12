package ua.rivnegray.boardgames_shop.utils.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ua.rivnegray.boardgames_shop.utils.validation.ValidationConstants;
import ua.rivnegray.boardgames_shop.utils.validation.annotation.FirstNameAndLastName;

import java.util.regex.Pattern;

public class FirstNameAndLastNameValidator implements ConstraintValidator<FirstNameAndLastName, String> {
    private Pattern pattern;

    @Override
    public void initialize(FirstNameAndLastName constraintAnnotation) {
        pattern = Pattern.compile(ValidationConstants.firstNameAndLastNameRegex);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return false;

        return pattern.matcher(value).matches();
    }
}
