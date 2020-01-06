package com.nriagudubem.recommenderspring.security.exceptions;

public class MalformedBearerTokenException extends RuntimeException {
    public MalformedBearerTokenException(String message) {
        super(message);
    }

    public MalformedBearerTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
