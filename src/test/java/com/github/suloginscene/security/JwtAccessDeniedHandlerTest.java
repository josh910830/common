package com.github.suloginscene.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;


@DisplayName("JWT 접근 거부 핸들러")
class JwtAccessDeniedHandlerTest {

    JwtAccessDeniedHandler jwtAccessDeniedHandler;

    MockHttpServletRequest req;
    MockHttpServletResponse res;


    @BeforeEach
    void setup() {
        jwtAccessDeniedHandler = new JwtAccessDeniedHandler();

        req = new MockHttpServletRequest();
        res = new MockHttpServletResponse();
    }


    @Test
    @DisplayName("예외 처리 - 401 && 응답 본문 메시지")
    void handle_onSuccess_writeToResponse() throws UnsupportedEncodingException {
        Authentication authentication = new InvalidAuthentication("message");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AccessDeniedException e = new AccessDeniedException("trigger");
        jwtAccessDeniedHandler.handle(req, res, e);

        assertThat(res.getStatus()).isEqualTo(401);
        assertThat(res.getContentAsString()).isEqualTo("message");
    }

    @Test
    @DisplayName("예외 처리 IO 오류 - 401")
    void handle_onIOException_logError() throws UnsupportedEncodingException {
        Authentication authentication = new InvalidAuthentication("message");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        res = spy(res);
        PrintWriter printWriter = mock(PrintWriter.class);
        doAnswer(invocation -> {
            throw new IOException("io exception");
        }).when(printWriter).print(anyString());
        given(res.getWriter()).willReturn(printWriter);

        AccessDeniedException e = new AccessDeniedException("trigger");
        jwtAccessDeniedHandler.handle(req, res, e);

        assertThat(res.getStatus()).isEqualTo(401);
        assertThat(res.getContentAsString()).isEqualTo("");
    }

}
