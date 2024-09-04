package com.software.dux.challenge.service.exception;

import com.software.dux.challenge.model.ErrorMensaje;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMensaje> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorMensaje error = new ErrorMensaje("La solicitud es invalida", 400);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMensaje> handleGenericExceptions(RuntimeException ex) {
        ErrorMensaje error = new ErrorMensaje("La solicitud es invalida", 400);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
  
}
