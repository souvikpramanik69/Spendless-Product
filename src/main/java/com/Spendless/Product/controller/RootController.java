package com.Spendless.Product.controller;

import com.Spendless.Product.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {


    @GetMapping("/api/product")
    public ResponseEntity<ApiResponse<String,Object>> rootController(){
        ApiResponse<String,Object> response;
        try{
          response = new ApiResponse<String,Object>(200,"Welcome to Product Service", ApiResponse.Status.SUCCESS);
          return new ResponseEntity<ApiResponse<String,Object>>(response, HttpStatus.OK);
        }catch (Exception e){
            response = new ApiResponse<String,Object>(500,e.getMessage(), ApiResponse.Status.SUCCESS);
            return new ResponseEntity<ApiResponse<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
