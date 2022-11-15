package com.ht.penitancier.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class YearInvalidException extends RuntimeException {

    public YearInvalidException(String message) {
        super(message);
    }
}
