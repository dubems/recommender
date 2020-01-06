package com.nriagudubem.recommenderspring.boundary.controller.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String username) {
        super("no user for username=" + username);
    }
}
