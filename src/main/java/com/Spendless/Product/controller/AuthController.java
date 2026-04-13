package com.Spendless.Product.controller;

import com.Spendless.Product.dto.UserDto;
import com.Spendless.Product.payload.UserPayload;
import com.Spendless.Product.response.ApiResponse;
import com.Spendless.Product.service.authService.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@Tag(name = "Auth Api's",description = "Signup and Signin APIs")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/auth/signup")
    @Operation(summary = "Register new user", description = "Creates a new user account")
    public ResponseEntity<ApiResponse<UserDto>> signupController( @RequestBody UserPayload payload){

        ApiResponse<UserDto> response = new ApiResponse<UserDto>();
        UserDto data = authService.signupService(payload);
        response.setCode(201);
        response.setMessage("Signup Successfully");
        response.setData(data);
        response.setStatus(ApiResponse.Status.SUCCESS);return ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<ApiResponse<UserDto>> signinController( @RequestBody UserPayload payload){
        ApiResponse<UserDto> response = new ApiResponse<>();
        UserDto user = authService.singinService(payload);
        response.setStatus(ApiResponse.Status.SUCCESS);
        response.setCode(200);
        response.setData(user);
        response.setMessage("Login Successfully");
        return ResponseEntity.status(response.getCode()).body(response);
    }




}