package de.cogamemonolith.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 *Will be thrown if a service, which is called from another service is unavailable
 * e.g. event service calls message service, which is unreachable
 */
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ServiceUnavailable extends RuntimeException{
    private String message;

    public ServiceUnavailable(String s) {
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
