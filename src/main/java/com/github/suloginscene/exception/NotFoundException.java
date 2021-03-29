package com.github.suloginscene.exception;


public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> targetClass, Object id) {
        super(targetClass.getSimpleName() + ":" + id);
    }

}
