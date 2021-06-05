package com.github.suloginscene.exception;

import com.github.suloginscene.mail.ConsoleMailer;
import com.github.suloginscene.mail.Mailer;
import com.github.suloginscene.property.AppProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;


@DisplayName("에러 알림")
class ErrorNotifierTest {

    ErrorNotifier errorNotifier;

    Mailer mailer;
    AppProperties appProperties;


    @BeforeEach
    void setup() {
        mailer = spy(ConsoleMailer.class);
        appProperties = new AppProperties();
    }


    @Test
    @DisplayName("메일 성공")
    void onSuccess_sendMail() {
        errorNotifier = new ErrorNotifier(mailer, appProperties);

        errorNotifier.sendMailToDeveloper("errorLog");

        then(mailer).should().send(any());
    }

    @Test
    @DisplayName("메일 실패 - 예외 미발생")
    void onExternalException_doesNotThrow() {
        doThrow(ExternalException.class).when(mailer).send(any());
        errorNotifier = new ErrorNotifier(mailer, appProperties);

        Executable action = () -> errorNotifier.sendMailToDeveloper("errorLog");

        assertDoesNotThrow(action);
    }

}
