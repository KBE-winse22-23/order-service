package com.onlineshop.order.core.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long myOrderId;

    private String paymentId;

    private Long cartId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "owner_id",
            referencedColumnName = "ownerId"
    )
    private Owner owner;


    @OneToMany(mappedBy = "myOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderProductMap> orderProductMaps = new ArrayList<>();


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "address_id",
            referencedColumnName = "addressId"
    )
    private Address address;

    private Date date;

    private double totalPrice;

}
