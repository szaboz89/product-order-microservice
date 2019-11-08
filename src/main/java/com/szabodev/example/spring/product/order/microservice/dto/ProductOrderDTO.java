package com.szabodev.example.spring.product.order.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDTO {

    private Long id;

    @NotNull
    private Long productId;

    private String productName;

    @NotNull
    private Integer amount;
}
