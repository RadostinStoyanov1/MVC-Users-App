package usersmvc.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserDataNotValidated extends RuntimeException{

    public UserDataNotValidated(String message) {
        super(message);
    }

}
