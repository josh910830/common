package com.github.suloginscene.security;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static java.util.Collections.emptySet;


public class TemporalAuthentication extends UsernamePasswordAuthenticationToken {

    @Getter
    private final String message;


    public TemporalAuthentication(String message) {
        super("temporal", "", emptySet());
        this.message = message;
    }

}
