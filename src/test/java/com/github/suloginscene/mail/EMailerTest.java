package com.github.suloginscene.mail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;


@DisplayName("메일 - Mock 이메일")
class EMailerTest {

    EMailer eMailer;

    MailMessage message;


    @BeforeEach
    void setup() {
        String recipient = "scene@email.com";
        String title = "Title";
        String content = "Content...";
        message = new MailMessage(recipient, title, content);
    }


    @Test
    @DisplayName("위임 전송")
    void doSend() throws MessagingException {
        JavaMailSender javaMailSender = mock(JavaMailSender.class);
        given(javaMailSender.createMimeMessage()).willReturn(mock(MimeMessage.class));
        doAnswer(invocation -> null).when(javaMailSender).send(any(MimeMessage.class));
        eMailer = new EMailer(javaMailSender);

        eMailer.doSend(message);

        then(javaMailSender).should().send(any(MimeMessage.class));
    }

}