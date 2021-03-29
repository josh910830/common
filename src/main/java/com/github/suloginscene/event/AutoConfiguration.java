package com.github.suloginscene.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
class AutoConfiguration {

    private final ApplicationEventPublisher applicationEventPublisher;


    @Bean
    EventPublisher eventPublisher() {
        return new EventPublisher(applicationEventPublisher);
    }

}
