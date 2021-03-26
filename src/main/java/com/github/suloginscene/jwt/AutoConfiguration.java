package com.github.suloginscene.jwt;

import com.github.suloginscene.property.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
class AutoConfiguration {

    private final JwtProperties jwtProperties;


    @Bean
    JwtFactory jwtFactory() {
        return new JwtFactory(jwtProperties.getSecret());
    }

    @Bean
    JwtReader jwtReader() {
        return new JwtReader(jwtProperties.getSecret());
    }

}
