package com.my.bank.repository;

import com.my.bank.dto.BankAccountDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<BankAccountDto, Long> {
}
