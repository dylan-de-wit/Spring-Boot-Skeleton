package com.dylandewit.skeleton.exception.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(Long id) {
        this(id, "item");
    }

    public NotFoundException(String name) {
        super(name + " does not exist", HttpStatus.NOT_FOUND);
    }

    public NotFoundException(Long id, String className) {
        super("Requested " + className + " with id: " + id + " does not exist", HttpStatus.NOT_FOUND);
    }
}
