package com.github.suloginscene.security;

import com.github.suloginscene.property.SecurityProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsProcessor;
import org.springframework.web.cors.DefaultCorsProcessor;

import javax.servlet.FilterChain;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("CORS 설정")
class DefaultCorsConfigurationSourceTest {

    DefaultCorsConfigurationSource corsConfigurationSource;

    SecurityProperties securityProperties;

    MockHttpServletRequest req;
    MockHttpServletResponse res;
    FilterChain filters;

    CorsProcessor corsProcessor;


    @BeforeEach
    void setup() {
        securityProperties = new SecurityProperties();
        corsConfigurationSource = new DefaultCorsConfigurationSource(securityProperties);

        req = new MockHttpServletRequest();
        res = new MockHttpServletResponse();
        filters = new MockFilterChain();

        corsProcessor = new DefaultCorsProcessor();
    }


    @Test
    @DisplayName("Same Origin 요청 - 통과")
    void sameOrigin_isValid() throws IOException {
        CorsConfiguration corsConfiguration = corsConfigurationSource.getCorsConfiguration(req);
        boolean isValid = corsProcessor.processRequest(corsConfiguration, req, res);

        assertThat(isValid).isTrue();
    }

    @Test
    @DisplayName("허용 Origin 요청 - 통과")
    void allowedOrigins_areValid() throws IOException {
        String[] allowedOrigins = securityProperties.getOrigins().split(",");
        for (String origin : allowedOrigins) {
            req.addHeader("Origin", origin);

            String[] methods = {"GET", "POST", "PUT", "DELETE"};
            for (String method : methods) {
                req.setMethod(method);

                CorsConfiguration corsConfiguration = corsConfigurationSource.getCorsConfiguration(req);
                boolean isValid = corsProcessor.processRequest(corsConfiguration, req, res);

                assertThat(isValid).isTrue();
            }
        }
    }

    @Test
    @DisplayName("비허용 Origin 요청 - 차단")
    void notAllowedOrigins_invalid() throws IOException {
        req.addHeader("Origin", "https://invalid.com");
        req.setMethod("GET");

        CorsConfiguration corsConfiguration = corsConfigurationSource.getCorsConfiguration(req);
        boolean isValid = corsProcessor.processRequest(corsConfiguration, req, res);

        assertThat(isValid).isFalse();
    }

}
