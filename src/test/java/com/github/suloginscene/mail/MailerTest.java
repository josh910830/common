package com.github.suloginscene.mail;

import com.github.suloginscene.exception.ExternalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doAnswer;


@DisplayName("메일")
class MailerTest {

    Mailer mailer;

    MailMessage message;


    @BeforeEach
    void setup() {
        String recipient = "scene@email.com";
        String title = "Title";
        String content = "Content...";
        message = new MailMessage(recipient, title, content);
    }


    @Test
    @DisplayName("템플릿메서드 패턴")
    void callDoSend_byTemplateMethodPattern() throws MessagingException {
        mailer = Mockito.spy(ConsoleMailer.class);

        mailer.send(message);

        then(mailer).should().doSend(message);
    }

    @Test
    @DisplayName("템플릿메서드 패턴 - 예외")
    void catchAndThrow_onMessagingException() throws MessagingException {
        mailer = Mockito.spy(ConsoleMailer.class);
        doAnswer(invocation -> {
            throw new MessagingException();
        }).when(mailer).doSend(any());

        Executable action = () -> mailer.send(message);

        assertThrows(ExternalException.class, action);
    }

}
