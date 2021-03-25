package com.github.suloginscene.jwtconfig;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Slf4j
class HttpErrorUtils {

    static void printForbidden(ServletResponse servletRes, String message) {
        HttpServletResponse httpRes = (HttpServletResponse) servletRes;
        httpRes.setStatus(403);
        printMessage(httpRes, message);
    }

    private static void printMessage(HttpServletResponse httpRes, String message) {
        try (PrintWriter writer = httpRes.getWriter()) {
            writer.print(message);
        } catch (IOException e) {
            log.error("on print http response - {}", e.getMessage());
        }
    }

}
