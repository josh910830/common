package com.github.suloginscene.jjwthelper;

import io.jsonwebtoken.JwtException;


public class InvalidJwtException extends Exception {

    public InvalidJwtException(Class<? extends JwtException> aClass) {
        super(aClass.getSimpleName());
    }

}
