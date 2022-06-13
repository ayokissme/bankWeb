package com.my.bank.repository;

import com.my.bank.dto.BankAccountDto;
import com.my.bank.dto.TransactionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionDto, Long> {

    @Query("select t from TransactionDto t where t.recipientAccount = :acc or t.senderAccount = :acc")
    List<TransactionDto> findTransactions(@Param("acc") BankAccountDto bankAccount);
}
