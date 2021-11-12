package de.cogamemonolith.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;


/**
 * Defines common exception handlers for all RestControllers in the project
 * Once an exception occurs, the  response entity will be returned with the exception message
 */
@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Will be called when one of the requested resources is not found
     *
     * @param ex         exception which is occurred
     * @param webRequest gives access to request metadata
     * @return 404 status code and details of the exception
     */
    @ExceptionHandler({NotFoundException.class})
    public final ResponseEntity<Object> notFoundException
    (NotFoundException ex, WebRequest webRequest) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(), ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Will be called when number of participants reached
     *
     * @param ex         exception which is occurred
     * @param webRequest gives access to request metadata
     * @return 403 status code and details of the exception
     */
    @ExceptionHandler({NumberOfParticipantsReached.class})
    public final ResponseEntity<Object> numberOfParticipantsReached
    (NumberOfParticipantsReached ex, WebRequest webRequest) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(),
                ex.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * Will be called when unique key violation is occurred
     *
     * @param ex         exception which is occurred
     * @param webRequest gives access to request metadata
     * @return 403 status code and details of the exception
     */
    @ExceptionHandler({UniqueKeyViolation.class})
    public final ResponseEntity<Object> uniqueKeyViolation
    (UniqueKeyViolation ex, WebRequest webRequest) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(),
                ex.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse,  HttpStatus.CONFLICT);
    }

    /**
     * Will be called when a service does not response
     * e.g. event service calls message service, which is unreachable
     *
     * @param ex         exception which is occurred
     * @param webRequest gives access to request metadata
     * @return 403 status code and details of the exception
     */
    @ExceptionHandler({ServiceUnavailable.class})
    public final ResponseEntity<Object> unreachable
    (ServiceUnavailable ex, WebRequest webRequest) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(),
                ex.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse,  HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * Will be called, when an exception will be thrown which is not present in this class
     *
     * @param ex         exception which is occurred
     * @param webRequest gives access to request metadata
     * @return 404 status code and details of the exception
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> exception
    (NotFoundException ex, WebRequest webRequest) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(), ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Will be called, when the validation of an object in a POST or PUT methods fails
     *
     * @return 400 status code and details of the exception
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
    (MethodArgumentNotValidException ex,
     HttpHeaders headers,
     HttpStatus status,
     WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(), "Validation failed",
                ex.getBindingResult().toString());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    /**
     * Will be called, when the required request body is missing
     *
     * @return 400 status code and details of the exception
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable
    (HttpMessageNotReadableException ex,
     HttpHeaders headers,
     HttpStatus status,
     WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(), "Validation failed",
                "Required request body is missing");
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}
