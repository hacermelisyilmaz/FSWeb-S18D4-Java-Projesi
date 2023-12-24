package com.workintech.banking.service;

import com.workintech.banking.dao.CustomerDao;
import com.workintech.banking.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public Customer find(Long id) {
        Optional<Customer> optionalCustomer = customerDao.findById(id);
        if (optionalCustomer.isPresent()) return optionalCustomer.get();
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public Customer delete(Long id) {
        Customer customer = find(id);
        if (customer != null) {
            customerDao.delete(customer);
            return customer;
        }
        return null;
    }
}
