package com.trainingfresher.sampleservice.utils.exception;
import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
    public String message;
    public HttpStatus httpStatus;

    public BaseException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public BaseException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
