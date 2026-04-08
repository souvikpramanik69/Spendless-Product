package com.Spendless.Product.handler;

import com.Spendless.Product.controller.AuthController;
import com.Spendless.Product.exception.UserAlreadyExistException;
import com.Spendless.Product.exception.UserNotFoundException;
import com.Spendless.Product.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobal(Exception ex) {

        ApiResponse<String> response = new ApiResponse<>();
        response.setCode(500);
        response.setMessage("Something went wrong");
        response.setStatus(ApiResponse.Status.ERROR);

        return ResponseEntity.status(500).body(new ApiResponse<>(500,ex.getMessage(), ApiResponse.Status.SUCCESS));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFoundException(Exception ex,HttpServletRequest request) {
       String url = request.getRequestURI();
       String method = request.getMethod();
        return ResponseEntity.status(404)
                .body(new ApiResponse<>(
                        404,
                        "API endpoint not found - method - " + method +" -" +url,
                        ApiResponse.Status.ERROR
                ));
    }
}
