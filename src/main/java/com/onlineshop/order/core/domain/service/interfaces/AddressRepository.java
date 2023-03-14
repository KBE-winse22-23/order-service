package com.onlineshop.order.core.domain.service.interfaces;

import com.onlineshop.order.core.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
