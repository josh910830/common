package com.github.suloginscene.security;

import com.github.suloginscene.jwt.InvalidJwtException;
import com.github.suloginscene.jwt.JwtReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
@RequiredArgsConstructor
@Slf4j
public class JwtSecurityFilter extends GenericFilterBean {

    private final JwtReader jwtReader;


    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        try {
            String jwt = getXAuthToken(req);
            authenticate(jwt);
            chain.doFilter(req, res);
        } catch (InvalidJwtException e) {
            log.warn(e.getMessage());
            printForbidden(res, e.getMessage());
        }
    }

    private String getXAuthToken(ServletRequest servletReq) {
        HttpServletRequest httpReq = (HttpServletRequest) servletReq;
        return httpReq.getHeader("X-AUTH-TOKEN");
    }

    private void authenticate(String jwt) throws InvalidJwtException {
        if (isExistent(jwt)) {
            Authentication authentication = toAuthentication(jwt);
            setAuthentication(authentication);
        }
    }

    // TODO
    private boolean isExistent(String jwt) {
        return jwt != null && !jwt.isBlank();
    }

    private Authentication toAuthentication(String token) throws InvalidJwtException {
        String audience = jwtReader.getAudience(token);
        Principal principal = Principal.of(audience);
        return principal.token();
    }

    private void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    private void printForbidden(ServletResponse servletRes, String message) {
        HttpServletResponse httpRes = (HttpServletResponse) servletRes;
        httpRes.setStatus(403);
        printMessage(httpRes, message);
    }

    private void printMessage(HttpServletResponse httpRes, String message) {
        try (PrintWriter writer = httpRes.getWriter()) {
            writer.print(message);
        } catch (IOException e) {
            log.error("on print http response - {}", e.getMessage());
        }
    }

}
