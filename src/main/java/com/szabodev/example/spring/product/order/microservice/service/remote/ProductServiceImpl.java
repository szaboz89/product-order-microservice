package com.szabodev.example.spring.product.order.microservice.service.remote;

import com.szabodev.example.spring.product.order.microservice.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final RestTemplate restTemplate;

    @Value("${book.microservice.apiUrl}")
    private String apiUrl;

    @Override
    public List<ProductDTO> findAll() {
        ProductDTO[] products = null;
        try {
            products = restTemplate.getForObject(apiUrl + "/products?getAvailableCount=true", ProductDTO[].class);
        } catch (RestClientException e) {
            log.error("Cannot get products", e);
        }
        return products != null ? Arrays.asList(products) : Collections.emptyList();
    }

    @Override
    public Optional<ProductDTO> findById(Long id) {
        ProductDTO product = null;
        try {
            product = restTemplate.getForObject(apiUrl + "/products/" + id, ProductDTO.class);
        } catch (RestClientException e) {
            log.error("Cannot get product", e);
        }
        return Optional.ofNullable(product);
    }
}
