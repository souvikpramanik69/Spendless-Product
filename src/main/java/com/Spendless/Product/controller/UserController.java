package com.Spendless.Product.controller;

import com.Spendless.Product.dto.UserDto;
import com.Spendless.Product.payload.UserPayload;
import com.Spendless.Product.response.ApiResponse;
import com.Spendless.Product.service.userService.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String, UserDto>> signupController( @RequestBody UserPayload payload){
           ApiResponse<String,UserDto> data = userService.createUserService(payload);
           return ResponseEntity.status(data.getCode()).body(data);
    }


}
