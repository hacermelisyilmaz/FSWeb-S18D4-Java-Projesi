package com.workintech.banking.controller;

import com.workintech.banking.dto.AccountResponse;
import com.workintech.banking.dto.CustomerResponse;
import com.workintech.banking.entity.Account;
import com.workintech.banking.entity.Customer;
import com.workintech.banking.service.AccountService;
import com.workintech.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account find(@PathVariable Long id) {
        return accountService.find(id);
    }

    @PostMapping("/{customerId}")
    public AccountResponse save(@PathVariable Long customerId, @RequestBody Account account) {
        Customer customer = customerService.find(customerId);
        if (customer != null) {
            customer.getAccounts().add(account);
            account.setCustomer(customer);
            accountService.save(account);
        }
        else throw new RuntimeException("Customer not found.");

        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(),
                new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName()));
    }

    @PutMapping("/{customerId}")
    public AccountResponse update(@PathVariable Long customerId, @RequestBody Account account) {
        Customer customer = customerService.find(customerId);
        Account existingAccount = null;

        for (Account acc: customer.getAccounts()) {
            if(Objects.equals(acc.getId(), account.getId())) {
                existingAccount = acc;
            }
        }

        if (existingAccount == null) throw new RuntimeException("Account (ID: " + account.getId() + ") not found for the customer (ID: " + customerId +")");

        int indexOfAccount = customer.getAccounts().indexOf(existingAccount);
        customer.getAccounts().set(indexOfAccount, account);
        accountService.save(account); // eski accounta ne oldu? silindi mi?

        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(),
                new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName()));
    }

    @DeleteMapping("/{id}")
    public AccountResponse remove(@PathVariable long id) {
        Account account = accountService.find(id);
        if (account == null ) throw new RuntimeException("Account with this ID not found: " + id);
        else {
            accountService.delete(id);
            Customer customer = account.getCustomer();
            return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(),
                    new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName()));
        }
    }
}
