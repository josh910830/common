package com.github.suloginscene.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;

import static com.github.suloginscene.profile.Profiles.LOCAL;
import static com.github.suloginscene.profile.Profiles.PROD;
import static com.github.suloginscene.profile.Profiles.TEST;


@Configuration
class AutoConfiguration {

    @Configuration
    @Profile({TEST, LOCAL})
    static class ConsoleMailerConfig {

        @Bean
        Mailer mailer() {
            return new ConsoleMailer();
        }

    }

    @Configuration
    @Profile(PROD)
    @RequiredArgsConstructor
    static class EMailerConfig {

        private final JavaMailSender javaMailSender;

        @Bean
        Mailer mailer() {
            return new EMailer(javaMailSender);
        }

    }

}
