package com.szabodev.example.spring.product.order.microservice.service.remote;

import com.szabodev.example.spring.product.order.microservice.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductServiceClient productServiceClient;

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> products = null;
        try {
            products = productServiceClient.findAll(true);
        } catch (RestClientException e) {
            log.error("Cannot get products", e);
        }
        return products != null ? products : Collections.emptyList();
    }

    @Override
    public Optional<ProductDTO> findById(Long id) {
        ProductDTO product = null;
        try {
            product = productServiceClient.findById(id);
        } catch (RestClientException e) {
            log.error("Cannot get product", e);
        }
        return Optional.ofNullable(product);
    }
}
