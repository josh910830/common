package com.github.suloginscene.jwt;

import com.github.suloginscene.property.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
class AutoConfiguration {

    private final SecurityProperties securityProperties;


    @Bean
    JwtFactory jwtFactory() {
        return new JwtFactory(securityProperties);
    }

    @Bean
    JwtReader jwtReader() {
        return new JwtReader(securityProperties);
    }

}
