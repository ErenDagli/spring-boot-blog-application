package com.dagli.springboot.exception;

public class PaymentException extends RuntimeException{

    public PaymentException(String message){
        super(message);
    }
}
