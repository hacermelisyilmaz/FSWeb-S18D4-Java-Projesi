package com.workintech.banking.dao;

import com.workintech.banking.entity.Account;
import com.workintech.banking.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<Account, Long> {
}
