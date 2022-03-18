package com.trainingfresher.sampleservice.utils.exception;


import org.springframework.http.HttpStatus;


public class NotFoundException extends BaseException {

    private static final long serialVersionUID = 3205010719953590317L;

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
