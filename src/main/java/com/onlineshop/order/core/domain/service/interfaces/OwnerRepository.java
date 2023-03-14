package com.onlineshop.order.core.domain.service.interfaces;

import com.onlineshop.order.core.domain.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Optional<Owner> findByEmail(String email);
}
