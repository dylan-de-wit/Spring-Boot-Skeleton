package com.dylandewit.skeleton.exception.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private HttpStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    private String debugMessage;


    public ApiError(HttpStatus status, Throwable ex) {
        this(status, "Unexpected error", ex);
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();

        if (ex != null)
            this.debugMessage = ex.getLocalizedMessage();
    }
}
