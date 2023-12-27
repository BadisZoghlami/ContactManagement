package com.contact.management.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContactValidationException extends RuntimeException {
    public ContactValidationException(String message) {
        super(message);
    }
}
