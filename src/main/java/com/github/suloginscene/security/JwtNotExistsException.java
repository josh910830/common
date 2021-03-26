package com.github.suloginscene.security;


class JwtNotExistsException extends Exception {

    JwtNotExistsException() {
        super("Jwt Not Exists");
    }

}
