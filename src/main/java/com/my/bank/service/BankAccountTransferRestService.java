package com.my.bank.service;

import com.my.bank.dto.BankAccountDto;
import com.my.bank.dto.CustomerDto;
import com.my.bank.repository.AccountRepository;
import com.my.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@Service
public class BankAccountTransferRestService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> transferByPhone(String recipientPhoneNumber, CustomerDto sender, BankAccountDto bankAccountDto, Double amount) {
        Optional<CustomerDto> optional = userRepository.findByPhoneNumber(recipientPhoneNumber);
        if (optional.isEmpty()) return new ResponseEntity<>("The person was not found", NO_CONTENT);
        CustomerDto recipient = optional.get();
        List<BankAccountDto> recipientAccounts = accountRepository.findAllByCustomer(recipient);
        if (recipientAccounts.size() == 0) return new ResponseEntity<>("The person does not have a bank account", NO_CONTENT);
        if (recipientAccounts.size() == 1) return new ResponseEntity<>("The person does not have a bank account", OK);
        return new ResponseEntity<>("", OK);
    }

    public ResponseEntity<?> transferByAccountNumber(Long recipientAccountNumber, CustomerDto sender, BankAccountDto bankAccountDto, Double amount) {
        return new ResponseEntity<>("", OK);
    }

//    public boolean doesBankAccountBelongsToSender(CustomerDto sender, BankAccountDto bankAccount) {
//
//    }
}

