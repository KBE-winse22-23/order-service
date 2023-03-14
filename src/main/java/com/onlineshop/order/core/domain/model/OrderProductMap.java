package com.onlineshop.order.core.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderProductMap {
    @EmbeddedId
    private OrderProductMapId orderProductMapId = new OrderProductMapId();

    @ManyToOne
    @MapsId("myOrderId")
    @JoinColumn(name = "my_order_id")
    @JsonIgnore
    private MyOrder myOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

}
