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
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ownerId;
    private String ownerFirstName;
    private String ownerLastname;
    private String email;


    @OneToMany(mappedBy="owner")
    @JsonIgnore
    private List<MyOrder> myOrders;

}
