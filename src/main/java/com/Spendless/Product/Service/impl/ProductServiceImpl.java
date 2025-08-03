package com.Spendless.Product.Service.impl;

import com.Spendless.Product.Model.Product;
import com.Spendless.Product.Payload.ProductPayload;
import com.Spendless.Product.Repository.ProductRepository;
import com.Spendless.Product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repository;

    public Product addProduct(ProductPayload payload){

        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName(payload.getName());
        product.setPrice(payload.getPrice());
        product.setItem(payload.getItem());
        return  repository.save(product);

    }
}
