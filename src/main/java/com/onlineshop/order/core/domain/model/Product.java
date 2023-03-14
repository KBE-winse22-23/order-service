package com.onlineshop.order.core.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Product {

    @Id
    private Long productId;
    private String productName;
    private double productPrice;
    private String image;

    public Product(Long productId, String productName, double productPrice, String image){
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.image = image;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderProductMap> orderProductMaps = new ArrayList<>();


}
