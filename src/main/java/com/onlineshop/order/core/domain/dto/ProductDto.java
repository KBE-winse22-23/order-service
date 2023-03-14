package com.onlineshop.order.core.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductDto {
    private Long productId;
    private String productName;
    private double productPrice;
    private String image;
    private int productQuantity;
}
