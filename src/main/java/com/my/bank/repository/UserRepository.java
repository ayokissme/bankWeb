package com.my.bank.repository;

import com.my.bank.dto.BankAccountDto;
import com.my.bank.dto.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CustomerDto, Long> {
    Optional<CustomerDto> findByPhoneNumber(String phone);
}
