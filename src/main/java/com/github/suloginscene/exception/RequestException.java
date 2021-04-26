package com.github.suloginscene.exception;


public abstract class RequestException extends RuntimeException {

    protected RequestException(String message) {
        super(message);
    }

}
