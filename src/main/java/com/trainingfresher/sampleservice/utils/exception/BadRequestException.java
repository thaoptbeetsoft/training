package com.trainingfresher.sampleservice.utils.exception;
import org.springframework.http.HttpStatus;


public class BadRequestException extends BaseException {
    private static final long serialVersionUID = -3541506651550285548L;

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
