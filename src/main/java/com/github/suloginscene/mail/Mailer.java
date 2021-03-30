package com.github.suloginscene.mail;

import com.github.suloginscene.exception.ExternalException;

import javax.mail.MessagingException;


public abstract class Mailer {

    public void send(MailMessage message) {
        try {
            doSend(message);
        } catch (MessagingException e) {
            throw new ExternalException(e.getMessage());
        }
    }

    protected abstract void doSend(MailMessage message) throws MessagingException;

}
