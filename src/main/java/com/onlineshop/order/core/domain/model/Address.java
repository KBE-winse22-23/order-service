package com.onlineshop.order.core.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    private String street;
    private String number;
    private String code;
    private String city;
    private String country;

    @OneToMany(mappedBy="address")
    @JsonIgnore
    private List<MyOrder> myOrders;
}
