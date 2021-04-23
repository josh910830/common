package com.github.suloginscene.security;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static java.util.Collections.emptySet;


class InvalidAuthentication extends UsernamePasswordAuthenticationToken {

    @Getter
    private final String message;


    InvalidAuthentication(String message) {
        super("invalid", "", emptySet());
        this.message = message;
    }

}
