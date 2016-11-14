package ru.spb.iac.storager.server.errors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import ru.spb.iac.storager.server.errors.common.NotAllowedReason;
import ru.spb.iac.storager.server.errors.common.ResourceUnavailableReason;
import ru.spb.iac.storager.server.errors.common.UnknownErrorReason;
import ru.spb.iac.storager.server.errors.shared.Reason;
import ru.spb.iac.storager.server.errors.shared.ReasonableException;
import ru.spb.iac.storager.server.errors.security.SecurityException;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class ErrorHandler {

//    private final Log log = LogFactory.getLog(ErrorHandler.class);
    private final Logger logger = Logger.getLogger(ErrorHandler.class.getCanonicalName());

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNotFound(final NoHandlerFoundException exception) {
        logAsError(exception);
        final Reason reason = new ResourceUnavailableReason(exception);
        return new ResponseEntity<>(reason, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleMethodNotAllowed(final HttpRequestMethodNotSupportedException exception) {
        logAsError(exception);
        final Reason reason = new NotAllowedReason(exception);
        return new ResponseEntity<>(reason, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<?> handleNotAuthorized(final SecurityException exception) {
        logAsError(exception);
        final Reason reason = exception.getReason();
        return new ResponseEntity<>(reason, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ReasonableException.class)
    public ResponseEntity<?> handleBusinessLogicException(final ReasonableException exception) {
        logAsError(exception);
        final Reason reason = exception.getReason();
        return new ResponseEntity<>(reason, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleUnknownException(final Throwable throwable) {
        logAsError(throwable);
        final UnknownErrorReason reason = new UnknownErrorReason();
        return new ResponseEntity<>(reason, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logAsError(final Throwable throwable) {
//        System.out.println("Error occurred: " + throwable.getMessage());
        logger.log(Level.WARNING, "Error occurred:", throwable);
    }
}
