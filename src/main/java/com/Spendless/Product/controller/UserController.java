package com.Spendless.Product.controller;

import com.Spendless.Product.dto.UserDto;
import com.Spendless.Product.payload.UserPayload;
import com.Spendless.Product.response.ApiResponse;
import com.Spendless.Product.service.authService.AuthService;
import com.Spendless.Product.service.userService.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "User App's", description = "All User API's here")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    @Operation(summary = "Get all users", description = "Here you can get all users with all sections")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers(){
     return  ResponseEntity.status(200).body(new ApiResponse<>(200,"All users has been retrieved", ApiResponse.Status.SUCCESS,userService.getAllUsers()));
    }

    @DeleteMapping("/user/{id}")
    @Operation(summary = "Delete user by uuid", description = "Here you can delete user by uuid")
    public ResponseEntity<ApiResponse<String>> deleteUserById(@PathVariable UUID id){
        return ResponseEntity.status(200).body(new ApiResponse<>(200, userService.deleteUserById(id), ApiResponse.Status.SUCCESS));
    }

    @PutMapping("/user")
    @Operation(summary = "Update users", description = "Put required fields")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@RequestBody UserPayload payload){
        return ResponseEntity.status(200).body(new ApiResponse<>(200,"User updated successfully", ApiResponse.Status.SUCCESS,userService.updateUser(payload)));
    }





}
