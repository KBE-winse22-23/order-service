package com.onlineshop.order.core.domain.service.impl;

import com.onlineshop.order.core.domain.model.Owner;
import com.onlineshop.order.core.domain.service.interfaces.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public List<Owner> getOwners() {
        return ownerRepository.findAll();
    }


    public Owner saveOwner(Owner owner) {
        Optional<Owner> findOwner = ownerRepository.findByEmail(owner.getEmail());

        return findOwner.orElseGet(() -> ownerRepository.save(owner));
    }


    public void deleteOwner(Owner owner) {
        ownerRepository.delete(owner);
    }

    public Owner updateOwner(Owner owner, Long ownerId) {
        return null;
    }
}
