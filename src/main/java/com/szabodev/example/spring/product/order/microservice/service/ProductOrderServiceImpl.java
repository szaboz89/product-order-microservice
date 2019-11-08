package com.szabodev.example.spring.product.order.microservice.service;

import com.szabodev.example.spring.product.order.microservice.dto.ProductOrderDTO;
import com.szabodev.example.spring.product.order.microservice.model.ProductOrder;
import com.szabodev.example.spring.product.order.microservice.repository.ProductOrderRepository;
import com.szabodev.example.spring.product.order.microservice.service.mapper.ProductOrderMapper;
import com.szabodev.example.spring.product.order.microservice.service.remote.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService {

    private final ProductOrderRepository productOrderRepository;

    private final ProductOrderMapper productOrderMapper;

    private final ProductService productService;

    @Value("${book.microservice.apiUrl}")
    private String apiUrl;

    @Override
    public Optional<ProductOrderDTO> findById(Long id) {
        Optional<ProductOrder> byId = productOrderRepository.findById(id);
        return Optional.ofNullable(productOrderMapper.toDTO(byId.orElse(null)));
    }

    @Override
    public Optional<ProductOrderDTO> findByProductId(Long id) {
        Optional<ProductOrder> byId = productOrderRepository.findByProductId(id);
        return Optional.ofNullable(productOrderMapper.toDTO(byId.orElse(null)));
    }

    @Override
    public List<ProductOrderDTO> findAll(boolean getProductInfo) {
        List<ProductOrderDTO> productOrders = productOrderMapper.toDTOs((List<ProductOrder>) productOrderRepository.findAll());
        productOrders.forEach(order -> productService.findById(order.getProductId()).ifPresent(product -> order.setProductName(product.getName())));
        return productOrders;
    }

    @Override
    public void deleteById(Long id) {
        productOrderRepository.deleteById(id);
    }

    @Override
    public ProductOrderDTO save(ProductOrderDTO productOrder) {
        ProductOrder saved = productOrderRepository.save(productOrderMapper.toEntity(productOrder));
        return productOrderMapper.toDTO(saved);
    }
}
