package ua.rivnegray.boardgames_shop.utils.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ua.rivnegray.boardgames_shop.utils.validation.validator.NameAndSurnameValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NameAndSurnameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NameAndSurname {
    String message() default "Invalid name and surname format!";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
