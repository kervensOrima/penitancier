package com.ht.penitancier.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundRole extends RuntimeException{

    public UserNotFoundRole(String m) {
        super(m);
    }
}
