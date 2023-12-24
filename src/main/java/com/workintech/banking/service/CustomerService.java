package com.workintech.banking.service;

import com.workintech.banking.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer find(Long id);
    Customer save(Customer customer);
    Customer delete(Long id);
}
