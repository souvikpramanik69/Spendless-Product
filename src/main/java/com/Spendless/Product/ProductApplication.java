package com.Spendless.Product;

import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;


@SpringBootApplication
@EntityScan("com.Spendless.Product.model")
public class ProductApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ProductApplication.class, args);
		Environment env = context.getEnvironment();
		String port = env.getProperty("server.port");
		System.out.println("Server Started Port on "+ port +  " http://localhost:"+port+"/api/v1"  );
		System.out.println("Api Docs  " + "http://localhost:"+port+"/docs"  );
	}

}
