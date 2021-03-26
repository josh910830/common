package com.github.suloginscene.jwt;

import io.jsonwebtoken.JwtException;


public class InvalidJwtException extends Exception {

    InvalidJwtException(Class<? extends JwtException> aClass) {
        super(aClass.getSimpleName());
    }

}
