package com.github.suloginscene.property;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
class AutoConfiguration {

    @Bean
    SecurityProperties securityProperties() {
        return new SecurityProperties();
    }

    @Bean
    AppProperties appProperties() {
        return new AppProperties();
    }

}
