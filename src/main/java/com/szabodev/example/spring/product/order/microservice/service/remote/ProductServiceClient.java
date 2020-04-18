package com.szabodev.example.spring.product.order.microservice.service.remote;

import com.szabodev.example.spring.product.order.microservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product-microservice", url = "${product.microservice.apiUrl}")
public interface ProductServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/products/{id}", produces = "application/json")
    ProductDTO findById(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/products", produces = "application/json")
    List<ProductDTO> findAll(@RequestParam boolean getAvailableCount);
}
