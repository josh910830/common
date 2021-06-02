package com.github.suloginscene.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
@Slf4j
class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        InvalidAuthentication invalid = (InvalidAuthentication) authentication;
        String message = invalid.getMessage();

        sendUnauthorized(res, message);
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
