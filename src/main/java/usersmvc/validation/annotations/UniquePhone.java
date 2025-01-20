package usersmvc.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import usersmvc.validation.validators.UniqueEmailValidator;
import usersmvc.validation.validators.UniquePhoneValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniquePhoneValidator.class)
public @interface UniquePhone {
    String message() default "Such phone already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
