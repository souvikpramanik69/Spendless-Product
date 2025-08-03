package com.Spendless.Product.Service;


import com.Spendless.Product.Model.Product;
import com.Spendless.Product.Payload.ProductPayload;


public interface ProductService {

    public Product addProduct(ProductPayload payload);

}
