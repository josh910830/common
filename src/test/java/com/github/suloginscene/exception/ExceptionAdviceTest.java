package com.github.suloginscene.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@DisplayName("예외 핸들러")
class ExceptionAdviceTest {

    ExceptionAdvice exceptionAdvice;


    @BeforeEach
    void setup() {
        exceptionAdvice = new ExceptionAdvice();
    }


    @Test
    @DisplayName("바인드 예외 - 요청 예외")
    void onBindException_returns400WithErrorResponse() {
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
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

}
