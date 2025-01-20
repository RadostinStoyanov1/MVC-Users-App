package usersmvc.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import usersmvc.service.impl.UserEntityServiceImpl;
import usersmvc.validation.annotations.UniquePhone;

@Component
public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String> {

    private final UserEntityServiceImpl userEntityService;

    public UniquePhoneValidator(UserEntityServiceImpl userEntityService) {
        this.userEntityService = userEntityService;
    }

    @Override
    public void initialize(UniquePhone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null || phone.isEmpty()) {
            return true;
        }
        return userEntityService.isPhoneNumberUnique(phone);
    }
}
