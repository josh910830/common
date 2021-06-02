package com.github.suloginscene.security;

import com.github.suloginscene.property.SecurityProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Component
public class DefaultCorsConfigurationSource extends UrlBasedCorsConfigurationSource {

    public DefaultCorsConfigurationSource (SecurityProperties securityProperties){
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
