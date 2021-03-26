package com.github.suloginscene.security;

import com.github.suloginscene.property.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@RequiredArgsConstructor
class AutoConfiguration {

    private final JwtProperties jwtProperties;


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        String[] urls = jwtProperties.getUrls().split(",");

        CorsConfiguration configuration = new CorsConfiguration();
        for (String url : urls) {
            configuration.addAllowedOrigin(url);
        }
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    UserDetailsService userDetailsService() {
        return s -> null;
    }

}
