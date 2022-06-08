package com.my.bank.repository;

import com.my.bank.dto.BankAccountOpeningQueueDto;
import com.my.bank.dto.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccQueueRepository extends JpaRepository<BankAccountOpeningQueueDto, Long> {
    Optional<BankAccountOpeningQueueDto> findByCustomer(CustomerDto customer);
}
