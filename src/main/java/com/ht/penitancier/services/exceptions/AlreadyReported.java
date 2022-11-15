package com.ht.penitancier.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class AlreadyReported extends RuntimeException{

    public AlreadyReported(String message) {
        super(message);
    }
}
