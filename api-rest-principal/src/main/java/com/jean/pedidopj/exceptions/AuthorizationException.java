package com.jean.pedidopj.exceptions;

public class AuthorizationException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public AuthorizationException(String msg) {
        super((msg));
    }

    public AuthorizationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
