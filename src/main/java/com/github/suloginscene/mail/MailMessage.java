package com.github.suloginscene.mail;

import lombok.Data;


@Data
public class MailMessage {

    private final String recipient;
    private final String title;
    private final String content;

}
