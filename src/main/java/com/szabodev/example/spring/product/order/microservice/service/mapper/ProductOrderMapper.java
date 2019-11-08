package com.szabodev.example.spring.product.order.microservice.service.mapper;

import com.szabodev.example.spring.product.order.microservice.dto.ProductOrderDTO;
import com.szabodev.example.spring.product.order.microservice.model.ProductOrder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductOrderMapper {

    ProductOrderDTO toDTO(ProductOrder productOrder);

    List<ProductOrderDTO> toDTOs(List<ProductOrder> productOrders);

    ProductOrder toEntity(ProductOrderDTO productOrderDTO);
}
