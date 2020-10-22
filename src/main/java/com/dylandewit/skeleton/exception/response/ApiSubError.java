package com.dylandewit.skeleton.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;
}