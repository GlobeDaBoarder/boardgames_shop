package ua.rivnegray.boardgames_shop.utils.validation.annotation;

import jakarta.validation.Constraint;
import ua.rivnegray.boardgames_shop.utils.validation.validator.FirstNameAndLastNameValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = FirstNameAndLastNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstNameAndLastName {
    String message() default "Invalid name and surname format!";
}
