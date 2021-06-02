package com.github.suloginscene.security;

import com.github.suloginscene.property.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.PostConstruct;


@Component
@RequiredArgsConstructor
public class DefaultCorsConfigurationSource extends UrlBasedCorsConfigurationSource {

    private final SecurityProperties securityProperties;


    @PostConstruct
    void setup() {
        String[] origins = securityProperties.getOrigins().split(",");

        CorsConfiguration configuration = new CorsConfiguration();
        for (String origin : origins) {
            configuration.addAllowedOrigin(origin);
        }
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        super.registerCorsConfiguration("/**", configuration);
    }

}
