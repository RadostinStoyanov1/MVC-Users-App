package usersmvc.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
public class ExistingEmailOrPhoneException extends RuntimeException{

    public ExistingEmailOrPhoneException(String message) {
        super(message);
    }

}
