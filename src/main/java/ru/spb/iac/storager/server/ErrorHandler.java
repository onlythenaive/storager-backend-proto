package ru.spb.iac.storager.server;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ru.spb.iac.storager.server.security.NotAuthorizedException;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotAuthorizedException.class)
    protected ResponseEntity<Object> handleNotAuthorized(NotAuthorizedException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getReason(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
}
