package com.workintech.banking.controller;

import com.workintech.banking.dto.CustomerResponse;
import com.workintech.banking.entity.Account;
import com.workintech.banking.entity.Customer;
import com.workintech.banking.service.AccountService;
import com.workintech.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final AccountService accountService;

    @Autowired
    public CustomerController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer find(@PathVariable Long id) {
        return customerService.find(id);
    }

    @PostMapping
    public CustomerResponse save(@RequestBody Customer customer) {
        boolean isExist = findAll().contains(customer);
        if (isExist) throw new RuntimeException("Customer with this ID already exists: " + customer.getId());
        else {
            customerService.save(customer);
            for (Account acc: customer.getAccounts()) {
                accountService.save(acc);
            }
            return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName());
        }
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id, @RequestBody Customer customer) {
        Customer existingCustomer = customerService.find(id);
        if (existingCustomer == null) throw new RuntimeException("Customer with the following ID does not exist: " + id);
        customerService.save(customer);
        return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName());
    }

    @DeleteMapping("/{id}")
    public CustomerResponse remove(@PathVariable long id) {
        Customer customer = customerService.find(id);
        if (customer == null) throw new RuntimeException("Customer with the following ID does not exist: " + id);
        else {
            customerService.delete(id);
            return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName());
        }
    }
}
