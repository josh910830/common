package com.github.suloginscene.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Slf4j
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String message = toMessage(authentication);

        logUnauthorized(req, message);
        sendUnauthorized(res, message);
    }

    private String toMessage(Authentication authentication) {
        if (!(authentication instanceof TemporalAuthentication)) {
            log.error("AccessDeniedException occurred on non TemporalAuthentication");
            return "";
        }

        TemporalAuthentication temporal = (TemporalAuthentication) authentication;
        return temporal.getMessage();
    }


    private void logUnauthorized(HttpServletRequest req, String message) {
        String ip = req.getRemoteAddr();
        int port = req.getRemotePort();
        String url = req.getRequestURI();

        log.warn("{}:{} try to access {}, but {}", ip, port, url, message);
    }


    private void sendUnauthorized(HttpServletResponse res, String message) {
        res.setStatus(401);
        printMessage(res, message);
    }

    private void printMessage(HttpServletResponse res, String message) {
        try (PrintWriter writer = res.getWriter()) {
            writer.print(message);
        } catch (IOException e) {
            log.error("on print http response - {}", e.getMessage());
        }
    }

}
