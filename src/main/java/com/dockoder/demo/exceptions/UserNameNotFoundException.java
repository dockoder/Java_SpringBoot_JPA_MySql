package com.dockoder.demo.exceptions;

public class UserNameNotFoundException extends Exception {
    public UserNameNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
