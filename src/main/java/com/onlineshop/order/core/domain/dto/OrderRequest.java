package com.onlineshop.order.core.domain.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderRequest {

    private String paymentId;
    private Long cartId;
    private String ownerFirstName;
    private String ownerLastName;
    private String email;
    private String addressStreet;
    private String addressNumber;
    private String addressCode;
    private String addressCity;
    private String addressCountry;
    private List<ProductDto> products;
}
