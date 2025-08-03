package com.Spendless.Product.Controller;

import com.Spendless.Product.Model.Product;
import com.Spendless.Product.Payload.ProductPayload;
import com.Spendless.Product.Service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {


    @Autowired
    ProductServiceImpl productService;

   @PostMapping("/add")
    public Product createProduct(@RequestBody ProductPayload product) {
      return  productService.addProduct(product);

    }
}
