package com.github.suloginscene.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.FAILED_DEPENDENCY;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@ControllerAdvice
@Slf4j
class ExceptionAdvice {

    // TODO mail on critical exception

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ErrorResponse> on(RequestException e) {
        log.warn(toLogString(e));
        return ResponseEntity
                .badRequest().body(ErrorResponse.of(e));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Void> on(ForbiddenException e) {
        log.warn(toLogString(e));
        return ResponseEntity
                .status(FORBIDDEN).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Void> on(NotFoundException e) {
        log.warn(toLogString(e));
        return ResponseEntity
                .status(NOT_FOUND).build();
    }

    @ExceptionHandler(ExternalException.class)
    public ResponseEntity<Void> on(ExternalException e) {
        log.error(toLogString(e));
        return ResponseEntity
                .status(FAILED_DEPENDENCY).build();
    }

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<Void> on(InternalException e) {
        log.error(toLogString(e));
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR).build();
    }

    private String toLogString(Exception e) {
        String className = e.getClass().getSimpleName();
        String message = e.getMessage();
        return className + "(" + message + ")";
    }


    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> on(BindException be) {
        RequestException re = toRequestException(be);
        return on(re);
    }

    private RequestException toRequestException(BindException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(joining(","));
        return new RequestBindException(message);
    }

}
