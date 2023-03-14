package com.onlineshop.order.core.domain.service.interfaces;

import com.onlineshop.order.core.domain.model.OrderProductMap;
import com.onlineshop.order.core.domain.model.OrderProductMapId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductMapRepository extends JpaRepository<OrderProductMap, OrderProductMapId> {
}
