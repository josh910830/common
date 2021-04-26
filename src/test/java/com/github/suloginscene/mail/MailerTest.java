package com.github.suloginscene.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.mail.MessagingException;

import static org.mockito.BDDMockito.then;


@DisplayName("메일")
class MailerTest {

    Mailer mailer;


    @Test
    @DisplayName("템플릿메서드 패턴")
    void callDoSend_byTemplateMethodPattern() throws MessagingException {
        mailer = Mockito.spy(ConsoleMailer.class);

        String recipient = "scene@email.com";
        String title = "[TEST] Title";
        String content = "Test Content...";
        MailMessage message = new MailMessage(recipient, title, content);
        mailer.send(message);

        then(mailer).should().doSend(message);
    }

}
