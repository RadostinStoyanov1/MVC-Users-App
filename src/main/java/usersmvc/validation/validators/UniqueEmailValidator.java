package usersmvc.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import usersmvc.service.impl.UserEntityServiceImpl;
import usersmvc.validation.annotations.UniqueEmail;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserEntityServiceImpl userEntityService;

    public UniqueEmailValidator(UserEntityServiceImpl userEntityService) {
        this.userEntityService = userEntityService;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return true;
        }
        return userEntityService.isEmailUnique(email);
    }
}
