package com.my.bank.repository;

import com.my.bank.dto.BankAccountDto;
import com.my.bank.dto.CustomerDto;
import com.my.bank.dto.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<BankAccountDto, Long> {
    List<BankAccountDto> findAllByCustomer(CustomerDto customer);
    List<BankAccountDto> findAllByCustomerAndStatus(CustomerDto customer, AccountStatus status);
    Optional<BankAccountDto> findByCardNumber(Long cardNumber);
}
