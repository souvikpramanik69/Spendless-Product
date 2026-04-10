package com.Spendless.Product.handler;

import com.Spendless.Product.exception.SectionAlreadyExistException;
import com.Spendless.Product.exception.SectionNotFoundException;
import com.Spendless.Product.exception.UserAlreadyExistException;
import com.Spendless.Product.exception.UserNotFoundException;
import com.Spendless.Product.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(404)
                .body(new ApiResponse<>(404, ex.getMessage(), ApiResponse.Status.ERROR));
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiResponse<String>> handleUserAlreadyExist(UserAlreadyExistException ex) {
        return ResponseEntity.status(409)
                .body(new ApiResponse<>(409, ex.getMessage(), ApiResponse.Status.ERROR));
    }

    @ExceptionHandler(SectionAlreadyExistException.class)
    public ResponseEntity<ApiResponse<String>> handleSectionAlreadyExist(SectionAlreadyExistException ex) {
        return ResponseEntity.status(409)
                .body(new ApiResponse<>(409, ex.getMessage(), ApiResponse.Status.ERROR));
    }

    @ExceptionHandler(SectionNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleSectionNotFound(SectionNotFoundException ex) {
        return ResponseEntity.status(404)
                .body(new ApiResponse<>(404, ex.getMessage(), ApiResponse.Status.ERROR));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobal(Exception ex) {

        ApiResponse<String> response = new ApiResponse<>();
        response.setCode(500);
        response.setMessage("Something went wrong");
        response.setStatus(ApiResponse.Status.ERROR);

        return ResponseEntity.status(500).body(new ApiResponse<>(500,ex.getMessage(), ApiResponse.Status.SUCCESS));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>>handleValidationException(MethodArgumentNotValidException ex){
        Map<String,Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(item->{
            errors.put(item.getField(),item.getDefaultMessage());

        });
        Map<String, Object> response = new HashMap<>();
        response.put("success","ERROR");
        response.put("code",400);
        response.put("errors",errors);
       return ResponseEntity.status(400).body(response);
    }


}
