package com.onlineshop.order.core.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DecreaseProductQuantityDto {
    private Long productId;
    private int quantity;
}
