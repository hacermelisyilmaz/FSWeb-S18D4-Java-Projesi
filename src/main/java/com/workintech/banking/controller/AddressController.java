package com.workintech.banking.controller;

import com.workintech.banking.dto.AddressResponse;
import com.workintech.banking.entity.Address;
import com.workintech.banking.service.AddressService;
import com.workintech.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;
    private final CustomerService customerService;

    @Autowired
    public AddressController(AddressService addressService, CustomerService customerService) {
        this.addressService = addressService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Address> findAll() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public Address find(@PathVariable Long id) {
        return addressService.find(id);
    }

    @PostMapping
    public AddressResponse save(@RequestBody Address address) {
        if (addressService.findAll().contains(address)) throw new RuntimeException("Address wit this ID already exists: " + address.getId());
        else {
            addressService.save(address);
            return new AddressResponse(address.getId(), address.getCountry(), address.getCity());
        }
    }

    @PutMapping("/{id}")
    public AddressResponse update(@PathVariable Long id, @RequestBody Address address) {
        Address existingAddress = addressService.find(id);
        if (existingAddress == null) throw new RuntimeException("Address with the following ID does not exist: " + id);
        addressService.save(address);
        return new AddressResponse(address.getId(), address.getCountry(), address.getCity());
    }

    @DeleteMapping("/{id}")
    public AddressResponse remove(@PathVariable Long id) {
        Address address = addressService.find(id);
        if (address == null) throw new RuntimeException("Address with the following ID does not exist: " + id);
        else {
            addressService.delete(id);
            return new AddressResponse(address.getId(), address.getCountry(), address.getCity());
        }
    }
}
