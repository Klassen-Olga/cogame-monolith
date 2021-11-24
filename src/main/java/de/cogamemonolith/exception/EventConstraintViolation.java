package de.cogamemonolith.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/*
* will be thrown if an forbidden activity in performed on event
* e.g. user wants to delete event, but is not the creator
* */
@ResponseStatus(HttpStatus.CONFLICT)
public class EventConstraintViolation extends RuntimeException {

    private String message;

    public EventConstraintViolation(String s) {
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}