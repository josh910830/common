package com.github.suloginscene.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FAILED_DEPENDENCY;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@DisplayName("예외 핸들러")
class ExceptionAdviceTest {

    ExceptionAdvice exceptionAdvice;


    @BeforeEach
    void setup() {
        exceptionAdvice = new ExceptionAdvice();
    }


    @Test
    @DisplayName("Request 예외 - 400 & 응답본문")
    void onRequestException_returns400WithErrorResponse() {
        RequestException requestException = mock(RequestException.class);
        given(requestException.getMessage()).willReturn("description");

        ResponseEntity<ErrorResponse> response = exceptionAdvice.on(requestException);

        assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getError()).isEqualTo(requestException.getClass().getSimpleName());
        assertThat(response.getBody().getErrorDescription()).isEqualTo("description");
    }

    @Test
    @DisplayName("Bind 예외 - 400 & 응답본문")
    void onBindException_returns400WithErrorResponse() {
        BindingResult bindingResult = mock(BindingResult.class);
        List<FieldError> fieldErrors = List.of(
                new FieldError("o1", "f1", "m1"),
                new FieldError("o2", "f2", "m2"));
        given(bindingResult.getFieldErrors()).willReturn(fieldErrors);
        BindException bindException = new BindException(bindingResult);

        ResponseEntity<ErrorResponse> response = exceptionAdvice.on(bindException);

        assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getError()).isEqualTo(RequestBindException.class.getSimpleName());
        assertThat(response.getBody().getErrorDescription()).isEqualTo("m1,m2");
    }

    @Test
    @DisplayName("Forbidden 예외 - 401")
    void onForbiddenException_returns401() {
        ForbiddenException forbiddenException = new ForbiddenException(new Object(), 1L);

        ResponseEntity<Void> response = exceptionAdvice.on(forbiddenException);

        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
    }

    @Test
    @DisplayName("NotFound 예외 - 404")
    void onNotFoundException_returns404() {
        NotFoundException notFoundException = new NotFoundException(Object.class, 1L);

        ResponseEntity<Void> response = exceptionAdvice.on(notFoundException);

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    @DisplayName("External 예외 - 424")
    void onExternalException_returns424() {
        ExternalException externalException = new ExternalException("reason");

        ResponseEntity<Void> response = exceptionAdvice.on(externalException);

        assertThat(response.getStatusCode()).isEqualTo(FAILED_DEPENDENCY);
    }

    @Test
    @DisplayName("Internal 예외 - 500")
    void onInternalException_returns500() {
        InternalException internalException = new InternalException("reason");

        ResponseEntity<Void> response = exceptionAdvice.on(internalException);

        assertThat(response.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);
    }

}
