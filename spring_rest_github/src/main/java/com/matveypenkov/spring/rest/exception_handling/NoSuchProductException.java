package com.matveypenkov.spring.rest.exception_handling;

public class NoSuchProductException extends RuntimeException{
    public NoSuchProductException(String message){
        super(message);
    }
}
