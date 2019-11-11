package com.szabodev.example.spring.product.order.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletedOrderDTO {

    private Long productId;

    private Integer amount;
}
