package com.github.suloginscene.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static com.github.suloginscene.profile.Profiles.PROD;
import static java.nio.charset.StandardCharsets.UTF_8;


@Component
@Profile(PROD)
@RequiredArgsConstructor
class EMailer extends Mailer {

    private final JavaMailSender javaMailSender;


    @Override
    protected void doSend(MailMessage message) throws MessagingException {
        MimeMessage mimeMessage = toMimeMessage(message);
        javaMailSender.send(mimeMessage);
    }

    private MimeMessage toMimeMessage(MailMessage message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, UTF_8.name());

        String recipient = message.getRecipient();
        String title = message.getTitle();
        String content = message.getContent();

        helper.setTo(recipient);
        helper.setSubject(title);
        helper.setText(content);

        return helper.getMimeMessage();
    }

}
