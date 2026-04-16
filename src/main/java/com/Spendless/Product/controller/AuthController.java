package com.Spendless.Product.controller;

import com.Spendless.Product.dto.AuthDto;
import com.Spendless.Product.dto.UserDto;
import com.Spendless.Product.model.Users;
import com.Spendless.Product.payload.UserPayload;
import com.Spendless.Product.response.ApiResponse;
import com.Spendless.Product.service.authService.AuthService;
import com.Spendless.Product.utils.JwtUtility;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@Tag(name = "Auth Api's",description = "Signup and Signin APIs")
public class AuthController {

    private final AuthService authService;

    @Autowired
    private JwtUtility jwtUtility;



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
    public ResponseEntity<ApiResponse<AuthDto>> signinController(@RequestBody UserPayload payload){
        ApiResponse<AuthDto> response = new ApiResponse<>();
        Users user = authService.singinService(payload);
        AuthDto dtoData = new AuthDto();
        dtoData.setAccessToken(jwtUtility.generateAccessToken(user.getEmail()));
        dtoData.setRefreshToken(jwtUtility.generateRefreshToken(user.getEmail()));
        dtoData.setId(user.getId());
        dtoData.setEmail(user.getEmail());
        dtoData.setName(user.getName());
        dtoData.setCreatedAt(user.getCreatedAt());
        dtoData.setUpdatedAt(user.getUpdatedAt());
        dtoData.setRole(user.getRole());
        response.setStatus(ApiResponse.Status.SUCCESS);
        response.setCode(200);
        response.setData(dtoData);
        response.setMessage("Login Successfully");
        return ResponseEntity.status(response.getCode()).body(response);
    }




}