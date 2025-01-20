package usersmvc.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import usersmvc.validation.validators.UniqueEmailValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "Such email already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
