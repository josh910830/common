package com.github.suloginscene.security;

import com.github.suloginscene.property.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@RequiredArgsConstructor
class AutoConfiguration {

    private final SecurityProperties securityProperties;


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        String[] origins = securityProperties.getOrigins().split(",");

        CorsConfiguration configuration = new CorsConfiguration();
        for (String origin : origins) {
            configuration.addAllowedOrigin(origin);
        }
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new JwtAccessDeniedHandler();
    }


    @Bean
    UserDetailsService userDetailsService() {
        return s -> null;
    }

}
