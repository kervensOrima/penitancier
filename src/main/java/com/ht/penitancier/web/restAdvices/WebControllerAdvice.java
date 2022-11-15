package com.ht.penitancier.web.restAdvices;

import com.ht.penitancier.services.exceptions.AgeInvalidException;
import com.ht.penitancier.services.exceptions.ObjectNotFoundException;
import com.ht.penitancier.services.exceptions.YearInvalidException;
import com.ht.penitancier.services.exceptions.AlreadyReported;
import com.ht.penitancier.utils.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@RestControllerAdvice
public class WebControllerAdvice {


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> methodeArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        HashMap<Object,ErrorMessage > map = new HashMap<>() ;
        ex.getFieldErrors().forEach( field -> {
            ErrorMessage error = ErrorMessage
                    .builder()
                    .code(400)
                    .message(ex.getMessage())
                    .timestamp(new Date())
                    .objectName(Objects.requireNonNull(ex.getFieldError()).getObjectName())
                    .build() ;
            map.put("errors", error);
        });
        return new ResponseEntity<>( map, HttpStatus.valueOf(400)) ;
    }

    @ExceptionHandler(value = {ObjectNotFoundException.class})
    public ResponseEntity<Object> objectNotFoundException(ObjectNotFoundException ex)
    {
        ErrorMessage error = ErrorMessage
                .builder()
                .code(404)
                .message(ex.getMessage())
                .timestamp(new Date())
                .objectName(ex.getClass().getName())
                .build() ;
        return new ResponseEntity<>(error, HttpStatus.valueOf(400)) ;
    }

    @ExceptionHandler(value = {AgeInvalidException.class})
    public ResponseEntity<Object> ageInvalidException(AgeInvalidException ex)
    {
        ErrorMessage error = ErrorMessage
                .builder()
                .code(500)
                .message(ex.getMessage())
                .timestamp(new Date())
                .objectName(ex.getClass().getName())
                .build() ;
        return new ResponseEntity<>(error, HttpStatus.valueOf(400)) ;
    }

    @ExceptionHandler(value = {YearInvalidException.class})
    public ResponseEntity<Object> yearInvalidException(YearInvalidException ex)
    {
        ErrorMessage error = ErrorMessage
                .builder()
                .code(500)
                .message(ex.getMessage())
                .timestamp(new Date())
                .build() ;
        return new ResponseEntity<>(error, HttpStatus.valueOf(400)) ;
    }


    @ExceptionHandler(value = {AlreadyReported.class})
    public ResponseEntity<Object> objectNotFoundException(AlreadyReported ex)
    {
        ErrorMessage error = ErrorMessage
                .builder()
                .code(208)
                .message(ex.getMessage())
                .timestamp(new Date())
                .objectName(ex.getClass().getName())
                .build() ;
        return new ResponseEntity<>(error, HttpStatus.valueOf(208)) ;
    }
}



