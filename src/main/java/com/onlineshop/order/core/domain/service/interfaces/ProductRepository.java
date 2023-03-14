package com.onlineshop.order.core.domain.service.interfaces;

import com.onlineshop.order.core.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
