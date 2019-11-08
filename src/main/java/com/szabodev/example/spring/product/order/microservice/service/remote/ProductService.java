package com.szabodev.example.spring.product.order.microservice.service.remote;

import com.szabodev.example.spring.product.order.microservice.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDTO> findAll();

    Optional<ProductDTO> findById(Long id);
}
