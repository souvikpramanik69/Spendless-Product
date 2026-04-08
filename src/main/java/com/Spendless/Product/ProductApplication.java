package com.Spendless.Product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.Spendless.Product.model")
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
		System.out.println("Server Started Port on  8009  " + "http://localhost:8009/api/v1"  );
	}

}
