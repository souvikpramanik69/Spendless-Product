package com.Spendless.Product.exception;

public class ExpenseNotFoundException extends  RuntimeException{

    public ExpenseNotFoundException(String message){
      super(message);
    }


}
