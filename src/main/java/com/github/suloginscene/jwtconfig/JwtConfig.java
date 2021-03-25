package com.github.suloginscene.jwtconfig;

import com.github.suloginscene.jjwthelper.JwtFactory;
import com.github.suloginscene.jjwthelper.JwtReader;
import com.github.suloginscene.jjwthelper.TestJwtFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
class JwtConfig {

    @Bean
    JwtProperties jwtProperties() {
        return new JwtProperties();
    }

    @Bean
    JwtFactory jwtFactory() {
        return new JwtFactory(jwtProperties().getSecret());
    }

    @Bean
    JwtReader jwtReader() {
        return new JwtReader(jwtProperties().getSecret());
    }

    @Bean
    JwtAuthenticator jwtAuthenticator() {
        return new JwtAuthenticator(jwtReader());
    }

    @Bean
    JwtSecurityFilter jwtSecurityFilter() {
        return new JwtSecurityFilter(jwtAuthenticator());
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        String[] urls = jwtProperties().getUrls().split(",");

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

    @Bean
    TestJwtFactory testJwtFactory() {
        return new TestJwtFactory(jwtFactory());
    }

}
