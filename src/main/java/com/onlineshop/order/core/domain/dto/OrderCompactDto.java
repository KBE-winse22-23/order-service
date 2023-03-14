package com.onlineshop.order.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderCompactDto {
    private Long orderId;
    private String paymentId;
    private Date date;
    private double totalPrice;
}
