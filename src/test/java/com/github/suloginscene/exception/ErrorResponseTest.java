package com.github.suloginscene.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("오류 응답")
class ErrorResponseTest {

    @Test
    @DisplayName("예외 구체 클래스 이름의 오류")
    void create_onSuccess_returnsErrorByClassName() {
        RequestException exception = new RequestBindException("message");

        ErrorResponse errorResponse = ErrorResponse.of(exception);

        String exceptionName = RequestBindException.class.getSimpleName();
        assertThat(errorResponse.getError()).isEqualTo(exceptionName);
    }

}
