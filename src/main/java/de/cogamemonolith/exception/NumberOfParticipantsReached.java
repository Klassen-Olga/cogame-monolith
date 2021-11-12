package de.cogamemonolith.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Will be thrown if user should be added to the event,
 * but the maximum number of participants has beenreached
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class NumberOfParticipantsReached extends RuntimeException {
    private String message;

    public NumberOfParticipantsReached(String s) {
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
