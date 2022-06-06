package com.my.bank.repository;

import com.my.bank.dto.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountDto, Long> {
}
