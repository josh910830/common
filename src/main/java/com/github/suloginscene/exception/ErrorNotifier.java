package com.github.suloginscene.exception;

import com.github.suloginscene.mail.MailMessage;
import com.github.suloginscene.mail.Mailer;
import com.github.suloginscene.property.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ErrorNotifier {

    private static final String TITLE = "[SCENE] 오류 발생";

    private final Mailer mailer;
    private final String developer;


    public ErrorNotifier(Mailer mailer, AppProperties appProperties) {
        this.mailer = mailer;
        this.developer = appProperties.getDeveloper();
    }


    public void sendMailToDeveloper(String errorLog) {
        MailMessage message = new MailMessage(developer, TITLE, errorLog);
        try {
            mailer.send(message);
        } catch (ExternalException ee) {
            log.debug("ExternalException on send mail to developer");
        }
    }

}
