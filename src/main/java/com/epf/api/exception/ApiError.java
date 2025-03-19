package com.epf.api.exception;

import org.springframework.http.HttpStatus;

public class ApiError extends RuntimeException {
    private final HttpStatus status;
    private final boolean success;
    private final String message;

    public ApiError(String message, HttpStatus status, boolean success) {
        super(message);
        this.message = message;
        this.status = status;
        this.success = success;
    }

    public ApiError(String message, HttpStatus status) {
        this(message, status, false);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String getMessage() {
        return message;
    }
}