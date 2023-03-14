package com.onlineshop.order.core.domain.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderDto {

    private Long orderId;
    private String paymentId;
    private List<ProductDto> products;
    private double totalPrice;
    private Date date;
    private String houseNumber;
    private String street;
    private String code;
    private String city;
    private String country;
    private String firstname;
    private String lastname;
}
