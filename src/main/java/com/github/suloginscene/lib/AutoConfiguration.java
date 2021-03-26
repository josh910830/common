package com.github.suloginscene.lib;

import com.github.suloginscene.lib.event.EventPublisher;
import com.github.suloginscene.lib.exception.ExceptionAdvice;
import com.github.suloginscene.lib.profile.ProfileChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
@RequiredArgsConstructor
class AutoConfiguration {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final Environment environment;


    @Bean
    EventPublisher eventPublisher() {
        return new EventPublisher(applicationEventPublisher);
    }

    @Bean
    ExceptionAdvice exceptionAdvice() {
        return new ExceptionAdvice();
    }

    @Bean
    ProfileChecker profileChecker() {
        return new ProfileChecker(environment);
    }

}
