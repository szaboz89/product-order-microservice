package com.szabodev.example.spring.product.order.microservice.service.remote;

import com.szabodev.example.spring.product.order.microservice.dto.OrderRequestDTO;
import com.szabodev.example.spring.product.order.microservice.dto.OrderResponseDTO;

public interface ProductStoreService {

    OrderResponseDTO requestOrder(OrderRequestDTO request);
}
