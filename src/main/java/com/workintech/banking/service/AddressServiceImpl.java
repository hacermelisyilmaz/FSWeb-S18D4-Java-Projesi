package com.workintech.banking.service;

import com.workintech.banking.dao.AddressDao;
import com.workintech.banking.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressDao addressDao;

    @Autowired
    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public List<Address> findAll() {
        return addressDao.findAll();
    }

    @Override
    public Address find(Long id) {
        Optional<Address> optionalAddress = addressDao.findById(id);
        if (optionalAddress.isPresent()) return optionalAddress.get();
        return null;
    }

    @Override
    public Address save(Address address) {
        return addressDao.save(address);
    }

    @Override
    public Address delete(Long id) {
        Address address = find(id);
        if (address != null) {
            addressDao.delete(address);
            return address;
        }
        return null;
    }
}
