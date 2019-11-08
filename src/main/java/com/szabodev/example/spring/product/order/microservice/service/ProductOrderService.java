package com.szabodev.example.spring.product.order.microservice.service;


import com.szabodev.example.spring.product.order.microservice.dto.ProductOrderDTO;

import java.util.List;
import java.util.Optional;

public interface ProductOrderService {

    Optional<ProductOrderDTO> findById(Long id);

    Optional<ProductOrderDTO> findByProductId(Long id);

    List<ProductOrderDTO> findAll(boolean getProductInfo);

    void deleteById(Long id);

    ProductOrderDTO save(ProductOrderDTO productOrder);
}
