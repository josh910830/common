package com.github.suloginscene.test;

import com.github.suloginscene.jwt.JwtFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class AutoConfiguration {

    private final JwtFactory jwtFactory;


    @Bean
    TestJwtFactory testJwtFactory() {
        return new TestJwtFactory(jwtFactory);
    }

}
