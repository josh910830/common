package com.github.suloginscene.mail;

import lombok.extern.slf4j.Slf4j;


@Slf4j
class ConsoleMailer extends Mailer {

    @Override
    protected void doSend(MailMessage message) {
        log.info("send mail\n" +
                format("recipient", message.getRecipient()) +
                format("title", message.getTitle()) +
                format("content", message.getContent()));
    }

    private String format(String key, String value) {
        return String.format("- %-9s : %s\n", key, value);
    }

}
