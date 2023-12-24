package com.workintech.banking.service;

import com.workintech.banking.dao.AccountDao;
import com.workintech.banking.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;

    @Autowired
    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account find(Long id) {
        Optional<Account> optionalAccount = accountDao.findById(id);
        if (optionalAccount.isPresent()) return optionalAccount.get();
        return null;
    }

    @Override
    public Account save(Account account) {
        return accountDao.save(account);
    }

    @Override
    public Account delete(Long id) {
        Account account = find(id);
        if (account != null) {
            accountDao.delete(account);
            return account;
        }
        return null;
    }
}
