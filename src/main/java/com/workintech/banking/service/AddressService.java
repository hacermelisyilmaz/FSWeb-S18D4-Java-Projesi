package com.workintech.banking.service;

import com.workintech.banking.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAll();
    Address find(Long id);
    Address save(Address address);
    Address delete(Long id);
}
