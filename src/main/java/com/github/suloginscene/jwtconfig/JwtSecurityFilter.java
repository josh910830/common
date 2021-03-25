package com.github.suloginscene.jwtconfig;

import com.github.suloginscene.jjwthelper.InvalidJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.github.suloginscene.jwtconfig.HttpErrorUtils.printForbidden;


@Component
@RequiredArgsConstructor
@Slf4j
public class JwtSecurityFilter extends GenericFilterBean {

    private final JwtAuthenticator jwtAuthenticator;


    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        try {
            String jwt = getXAuthToken(req);
            jwtAuthenticator.authenticate(jwt);
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

}
