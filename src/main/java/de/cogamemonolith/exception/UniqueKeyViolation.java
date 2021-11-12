package de.cogamemonolith.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
* Will be thrown if a field is marked as unique and is violated
* e.g. two users with the same email should be created
* */
@ResponseStatus(HttpStatus.CONFLICT)
public class UniqueKeyViolation extends RuntimeException{
    private String message;

    public UniqueKeyViolation(String s) {
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
