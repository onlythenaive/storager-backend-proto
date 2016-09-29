package ru.spb.iac.storager.server.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import ru.spb.iac.storager.server.errors.common.ResourceUnavailableReason;
import ru.spb.iac.storager.server.errors.common.UnknownErrorReason;
import ru.spb.iac.storager.server.errors.shared.Reason;
import ru.spb.iac.storager.server.errors.shared.ReasonableException;
import ru.spb.iac.storager.server.errors.security.SecurityException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNotFound(final NoHandlerFoundException exception) {
        final Reason reason = new ResourceUnavailableReason(exception);
        // TODO: log the exception
        return new ResponseEntity<>(reason, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<?> handleNotAuthorized(final SecurityException exception) {
        final Reason reason = exception.getReason();
        // TODO: log the exception
        return new ResponseEntity<>(reason, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ReasonableException.class)
    public ResponseEntity<?> handleBusinessLogicException(final ReasonableException exception) {
        final Reason reason = exception.getReason();
        // TODO: log the exception
        return new ResponseEntity<>(reason, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleUnknownException(final Throwable throwable) {
        final UnknownErrorReason reason = new UnknownErrorReason();
        // TODO: log the exception
        return new ResponseEntity<>(reason, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
