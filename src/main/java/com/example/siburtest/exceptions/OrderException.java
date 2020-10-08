package com.example.siburtest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class OrderException extends RuntimeException {
    public OrderException(String message) {
        super(message);
    }
}
