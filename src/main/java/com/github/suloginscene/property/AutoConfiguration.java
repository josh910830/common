package com.github.suloginscene.property;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
class AutoConfiguration {

    @Bean
    JwtProperties jwtProperties() {
        return new JwtProperties();
    }

}
