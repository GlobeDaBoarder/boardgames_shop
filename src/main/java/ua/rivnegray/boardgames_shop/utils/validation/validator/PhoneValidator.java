package ua.rivnegray.boardgames_shop.utils.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ua.rivnegray.boardgames_shop.utils.validation.ValidationConstants;
import ua.rivnegray.boardgames_shop.utils.validation.annotation.PhoneNumber;

import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<PhoneNumber, String> {
    private Pattern pattern;
    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        pattern = Pattern.compile(ValidationConstants.PHONE);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return false;

        return pattern.matcher(value).matches();
    }
}
