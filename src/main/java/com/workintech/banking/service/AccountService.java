package com.workintech.banking.service;

import com.workintech.banking.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account find(Long id);
    Account save(Account account);
    Account delete(Long id);
}
