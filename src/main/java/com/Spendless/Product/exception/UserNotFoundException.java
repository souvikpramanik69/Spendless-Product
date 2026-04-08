package com.Spendless.Product.exception;

public class UserNotFoundException extends  RuntimeException{

    public UserNotFoundException(String message){
        super(message);
    }
}
