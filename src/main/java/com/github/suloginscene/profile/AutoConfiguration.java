package com.github.suloginscene.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
@RequiredArgsConstructor
class AutoConfiguration {

    private final Environment environment;


    @Bean
    ProfileChecker profileChecker() {
        return new ProfileChecker(environment);
    }

}
