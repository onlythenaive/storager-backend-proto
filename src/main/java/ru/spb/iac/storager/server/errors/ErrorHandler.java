package ru.spb.iac.storager.server.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import ru.spb.iac.storager.server.security.errors.SecurityException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNotFound(final NoHandlerFoundException exception) {
        return new ResponseEntity<>(exception.getRequestURL(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<?> handleNotAuthorized(final SecurityException exception) {
        return new ResponseEntity<>(exception.getReason(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ReasonableException.class)
    public ResponseEntity<?> handleBusinessLogicException(final ReasonableException exception) {
        return new ResponseEntity<>(exception.getReason(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleUnknownException(final Throwable throwable) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
