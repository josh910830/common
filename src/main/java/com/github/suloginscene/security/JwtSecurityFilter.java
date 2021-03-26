package com.github.suloginscene.security;

import com.github.suloginscene.jwt.InvalidJwtException;
import com.github.suloginscene.jwt.JwtReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@RequiredArgsConstructor
@Slf4j
public class JwtSecurityFilter extends GenericFilterBean {

    private final JwtReader jwtReader;


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) req, (HttpServletResponse) res, chain);
    }

    private void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            authenticate(req);
            chain.doFilter(req, res);
        } catch (JwtNotExistsException | InvalidJwtException e) {
            logUnauthorized(req, e);
            sendUnauthorized(res, e);
        }
    }


    private void authenticate(HttpServletRequest req) throws JwtNotExistsException, InvalidJwtException {
        String jwt = getJwt(req);
        String audience = jwtReader.getAudience(jwt);
        Principal principal = Principal.of(audience);
        Authentication authentication = principal.token();
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getJwt(HttpServletRequest req) throws JwtNotExistsException {
        String jwt = req.getHeader("X-AUTH-TOKEN");
        if (jwt == null || jwt.isBlank()) {
            throw new JwtNotExistsException();
        }
        return jwt;
    }


    private void logUnauthorized(HttpServletRequest req, Exception e) {
        String ip = req.getRemoteAddr();
        int port = req.getRemotePort();
        String url = req.getRequestURI();

        log.warn("{}:{} try to access {}, but {}", ip, port, url, e.getMessage());
    }

    private void sendUnauthorized(HttpServletResponse res, Exception e) {
        res.setStatus(401);
        printMessage(res, e.getMessage());
    }

    private void printMessage(HttpServletResponse res, String message) {
        try (PrintWriter writer = res.getWriter()) {
            writer.print(message);
        } catch (IOException e) {
            log.error("on print http response - {}", e.getMessage());
        }
    }

}
