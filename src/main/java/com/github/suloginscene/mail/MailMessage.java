package com.github.suloginscene.mail;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class MailMessage {

    private final String recipient;
    private final String title;
    private final String content;

}
