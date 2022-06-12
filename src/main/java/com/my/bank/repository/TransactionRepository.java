package com.my.bank.repository;

import com.my.bank.dto.TransactionDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionDto, Long> {
}
