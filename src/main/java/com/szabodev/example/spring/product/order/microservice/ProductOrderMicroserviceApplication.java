package com.szabodev.example.spring.product.order.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProductOrderMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductOrderMicroserviceApplication.class, args);
    }

}
