package com.matveypenkov.spring.rest.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProductIncorrectData> handleException (NoSuchProductException exception){
        ProductIncorrectData data = new ProductIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ProductIncorrectData> handleException (Exception exception){
        ProductIncorrectData data = new ProductIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data,HttpStatus.BAD_REQUEST);
    }

}
