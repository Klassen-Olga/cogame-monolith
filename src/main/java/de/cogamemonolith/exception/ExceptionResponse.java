package de.cogamemonolith.exception;

import java.time.LocalDate;


/**
 * Provides standard response for all exceptions in the CustomExceptionHandler class
 */
public class ExceptionResponse {

    private LocalDate timestamp;
    private String message;
    private String details;

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public ExceptionResponse(LocalDate timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
