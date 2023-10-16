package com.devmare.blog_app_apis.exceptions;

public class ApiException extends RuntimeException {

    ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }
}
