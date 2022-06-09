package com.my.bank.service;

import com.my.bank.dto.BankAccountDto;
import com.my.bank.dto.BankAccountOpeningQueueDto;
import com.my.bank.dto.CustomerDto;
import com.my.bank.dto.enums.AccountStatus;
import com.my.bank.repository.AccQueueRepository;
import com.my.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.my.bank.dto.enums.AccountStatus.ACCEPTED;
import static com.my.bank.dto.enums.AccountStatus.REJECTED;

@Service
public class BankAccountRestService {

    @Autowired
    private AccQueueRepository queueRepository;

    @Autowired
    private AccountRepository bankAccountRepository;

    public ResponseEntity<?> sendRequestToAddToQueue(CustomerDto customer) {
        if (queueRepository.findByCustomer(customer).isPresent()) {
            return new ResponseEntity<>("You are already in line to open a bank account", HttpStatus.ALREADY_REPORTED);
        }
        BankAccountDto bankAccount = createRequestForOpeningAccount(customer);
        bankAccountRepository.save(bankAccount);
        queueRepository.save(new BankAccountOpeningQueueDto(customer, bankAccount));
        return new ResponseEntity<>("The application has been sent for consideration", HttpStatus.OK);
    }

    public ResponseEntity<?> sendRequestToDeleteFromQueue(BankAccountOpeningQueueDto accInQueue) {
        queueRepository.delete(accInQueue);
        return new ResponseEntity<>("The account has been deleted", HttpStatus.OK);
    }

    public ResponseEntity<?> acceptRequestForOpeningAccount(BankAccountOpeningQueueDto accInQueue) {
        changeStatusAndRemoveFromQueue(accInQueue, ACCEPTED);
        return new ResponseEntity<>("Bank account opening accepted", HttpStatus.OK);
    }

    public ResponseEntity<?> rejectRequestForOpeningAccount(BankAccountOpeningQueueDto accInQueue) {
        changeStatusAndRemoveFromQueue(accInQueue, REJECTED);
        return new ResponseEntity<>("Bank account opening rejected", HttpStatus.OK);
    }

    private void changeStatusAndRemoveFromQueue(BankAccountOpeningQueueDto accInQueue, AccountStatus status) {
        queueRepository.delete(accInQueue);
        BankAccountDto bankAccount = accInQueue.getBankAccount();
        bankAccount.setStatus(status);
        bankAccountRepository.save(bankAccount);
    }

    private BankAccountDto createRequestForOpeningAccount(CustomerDto customer) {
        List<Long> cardNumbers = bankAccountRepository.findAll()
                .stream()
                .map(BankAccountDto::getCardNumber)
                .collect(Collectors.toList());
        BankAccountDto bankAccount = new BankAccountDto();
        bankAccount.createCard(cardNumbers, customer);
        return bankAccount;
    }
}
