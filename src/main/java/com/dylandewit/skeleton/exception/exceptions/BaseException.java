package com.dylandewit.skeleton.exception.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {
    @Getter
    private HttpStatus statusCode;

    public BaseException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
