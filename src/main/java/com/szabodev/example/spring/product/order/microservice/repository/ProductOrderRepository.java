package com.szabodev.example.spring.product.order.microservice.repository;

import com.szabodev.example.spring.product.order.microservice.model.ProductOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long> {

    Optional<ProductOrder> findByProductId(Long id);
}
