package com.Spendless.Product.controller;

import com.Spendless.Product.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Root Api")
public class RootController {


    @GetMapping("/api/v1")
    public ResponseEntity<ApiResponse<String>> v1rootController(){
        ApiResponse<String> response;
        try{
          response = new ApiResponse<String>(200,"Welcome to Spendless Product Service", ApiResponse.Status.SUCCESS);
          return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.OK);
        }catch (Exception e){
            response = new ApiResponse<String>(500,e.getMessage(), ApiResponse.Status.SUCCESS);
            return new ResponseEntity<ApiResponse<String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api")
    public ResponseEntity<Void> rootController(){
      return  ResponseEntity.status(302)
                .header("Location", "/api/v1")
                .build();
    }

    @GetMapping("/")
    public ResponseEntity<Void> rootApiController(){
      return  ResponseEntity.status(302)
                .header("Location", "/api/v1")
                .build();
    }




}
