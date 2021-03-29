package com.github.suloginscene.exception;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
class AutoConfiguration {

    @Bean
    ExceptionAdvice exceptionAdvice() {
        return new ExceptionAdvice();
    }

}
