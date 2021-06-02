package com.github.suloginscene.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;


@AllArgsConstructor(access = PRIVATE)
class ErrorResponse {

    @Getter
    private final String error;

    @Getter
    private final String errorDescription;


    public static ErrorResponse of(RequestException e) {
        String className = e.getClass().getSimpleName();
        String message = e.getMessage();
        return new ErrorResponse(className, message);
    }

}
