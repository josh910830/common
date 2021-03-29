package com.github.suloginscene.exception;


public class ForbiddenException extends RuntimeException {

    public ForbiddenException(Object resource, Long memberId) {
        super(resource + " is not accessible to " + memberId);
    }

}
