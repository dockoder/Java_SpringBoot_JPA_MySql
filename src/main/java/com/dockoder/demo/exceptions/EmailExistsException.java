package com.dockoder.demo.exceptions;

public class EmailExistsException extends Exception {

    public EmailExistsException(String errorMessage) {
        super(errorMessage);
    }
}
