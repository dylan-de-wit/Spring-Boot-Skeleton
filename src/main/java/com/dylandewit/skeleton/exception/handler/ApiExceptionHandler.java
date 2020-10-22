package com.dylandewit.skeleton.exception.handler;

import com.dylandewit.skeleton.exception.exceptions.BaseException;
import com.dylandewit.skeleton.exception.response.ApiError;
import com.dylandewit.skeleton.exception.response.ApiSubError;
import com.dylandewit.skeleton.exception.response.ApiValidationError;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    public ApiExceptionHandler() {
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllUncaughtExceptions(Exception exception) {
        logger.info("Handled uncaught exception: ", exception);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception);
        apiError.setMessage("An unexpected error occurred, please try again later");

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<Object> handleUncaughtBaseException(BaseException exception) {
        log.info("Handled base exception: ", exception);
        ApiError apiError = new ApiError(exception.getStatusCode(), exception);
        apiError.setMessage(exception.getMessage());

        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info(exception);
        ApiValidationError apiError = new ApiValidationError(HttpStatus.UNPROCESSABLE_ENTITY);
        apiError.setMessage("The system was unable to process the information, because it encountered some validation errors");

        List<ApiSubError> validationErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map((FieldError error) -> new ApiSubError(error.getObjectName().replace("Dto", ""), error.getField(), error.getRejectedValue(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        apiError.setValidationErrors(validationErrors);

        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private ResponseEntity<Object> buildResponseEntity(ApiValidationError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
