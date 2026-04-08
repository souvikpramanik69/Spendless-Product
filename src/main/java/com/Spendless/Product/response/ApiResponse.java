package com.Spendless.Product.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ApiResponse<T,Object> {

    private int code;
    private T message;
    private java.lang.Object data;
    private Status status;
    public enum Status{
        ERROR,
        SUCCESS,
        PENDING
    }

    public ApiResponse(int code, T message, Status status,java.lang.Object data){
        this.code=code;
        this.message=message;
        this.status=status;
        this.data=data;
    }

    public ApiResponse(int code, T message,Status status){
        this.code=code;
        this.message=message;
        this.status=status;
    }







}
