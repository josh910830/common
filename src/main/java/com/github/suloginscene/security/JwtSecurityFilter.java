package com.github.suloginscene.security;

import com.github.suloginscene.jwt.InvalidJwtException;
import com.github.suloginscene.jwt.JwtReader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RequiredArgsConstructor
public class JwtSecurityFilter extends GenericFilterBean {

    private final JwtReader jwtReader;


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        authenticate(req);
        chain.doFilter(req, res);
    }

    private void authenticate(ServletRequest req) {
        try {
            String jwt = getJwt(req);
            String audience = jwtReader.getAudience(jwt);
            Principal principal = Principal.of(audience);
            Authentication authentication = principal.authentication();
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtNotExistsException | InvalidJwtException e) {
            String message = e.getMessage();
            Authentication invalid = new InvalidAuthentication(message);
            SecurityContextHolder.getContext().setAuthentication(invalid);
        }
    }

    private String getJwt(ServletRequest req) throws JwtNotExistsException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String jwt = httpRequest.getHeader("X-AUTH-TOKEN");
        if (jwt == null || jwt.isBlank()) {
            throw new JwtNotExistsException();
        }
        return jwt;
    }

}
