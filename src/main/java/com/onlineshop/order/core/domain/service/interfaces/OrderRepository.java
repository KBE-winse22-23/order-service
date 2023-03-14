package com.onlineshop.order.core.domain.service.interfaces;

import com.onlineshop.order.core.domain.model.MyOrder;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<MyOrder, Long> {

    List<MyOrder> findByOwnerEmail(String email);
    Optional<MyOrder> findByOwnerEmailAndPaymentId(String email, String paymentId);
}
