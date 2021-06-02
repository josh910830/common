package com.github.suloginscene.security;

import com.github.suloginscene.jwt.JwtFactory;
import com.github.suloginscene.jwt.JwtReader;
import com.github.suloginscene.property.SecurityProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("JWT 시큐리티 필터")
class JwtSecurityFilterTest {

    JwtSecurityFilter jwtSecurityFilter;

    JwtReader jwtReader;
    JwtFactory jwtFactory;

    MockHttpServletRequest req;
    MockHttpServletResponse res;
    FilterChain filters;


    @BeforeEach
    void setup() {
        SecurityProperties securityProperties = new SecurityProperties();
        jwtReader = new JwtReader(securityProperties);
        jwtFactory = new JwtFactory(securityProperties);
        jwtSecurityFilter = new JwtSecurityFilter(jwtReader);

        req = new MockHttpServletRequest();
        res = new MockHttpServletResponse();
        filters = new MockFilterChain();
    }


    @Test
    @DisplayName("정상")
    void filter_onPass_authAsPrincipal() throws ServletException, IOException {
        Long memberId = 1L;
        String jwt = jwtFactory.create(memberId);

        req.addHeader("X-AUTH-TOKEN", jwt);
        jwtSecurityFilter.doFilter(req, res, filters);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertThat(authentication.getPrincipal()).isEqualTo(Principal.of(memberId.toString()));
    }

    @Test
    @DisplayName("JWT 없음")
    void filter_onNotExists_authAsInvalid() throws ServletException, IOException {
        jwtSecurityFilter.doFilter(req, res, filters);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertThat(authentication.getPrincipal()).isEqualTo("invalid");
        assertThat(authentication.getClass()).isEqualTo(InvalidAuthentication.class);
        assertThat(((InvalidAuthentication) authentication).getMessage()).isEqualTo("Jwt Not Exists");
    }

}
