package usersmvc.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import usersmvc.service.exception.ExistingEmailOrPhoneException;
import usersmvc.service.exception.UserDataNotValidated;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(ExistingEmailOrPhoneException.class)
    public String handleExistingEmailOrPhone(ExistingEmailOrPhoneException eepe) {

        return "existing-email-or-phone";
    }
}
