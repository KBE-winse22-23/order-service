package com.onlineshop.order.core.domain.service.impl;

import com.onlineshop.order.core.domain.model.Address;
import com.onlineshop.order.core.domain.service.interfaces.AddressRepository;
import com.onlineshop.order.port.user.exception.EmptyFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService  {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }


    public Address saveAddress(Address address) throws EmptyFieldException {

        if(address.getCity().equalsIgnoreCase("")){
            throw new EmptyFieldException("Field city can't be empty!");
        } else if (address.getCountry().equalsIgnoreCase("")) {
            throw new EmptyFieldException("Field Country can't be empty!");
        }else if (address.getCode().equalsIgnoreCase("")) {
            throw new EmptyFieldException("Field Code can't be empty!");
        }else if (address.getNumber().equalsIgnoreCase("")) {
            throw new EmptyFieldException("Field House number can't be empty!");
        }else if (address.getStreet().equalsIgnoreCase("")) {
            throw new EmptyFieldException("Field Street can't be empty!");
        }

        return addressRepository.save(address);
    }


    public Address deleteAddress(Address address) {
        return null;
    }


    public Address updateAddress(Address address, Long addressId) {
        return null;
    }
}
