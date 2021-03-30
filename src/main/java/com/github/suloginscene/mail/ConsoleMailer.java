package com.github.suloginscene.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
class ConsoleMailer extends Mailer {

    @Override
    protected void doSend(MailMessage message) {
        log.info("send mail\n" +
                "\t" + message.toString() + "\n");
    }

}
