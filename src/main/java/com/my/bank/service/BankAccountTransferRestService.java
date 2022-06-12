package com.my.bank.service;

import com.my.bank.controller.ajaxResponseBody.CardToCardResponseBody;
import com.my.bank.dto.BankAccountDto;
import com.my.bank.dto.CustomerDto;
import com.my.bank.dto.TransactionDto;
import com.my.bank.dto.enums.AccountStatus;
import com.my.bank.repository.AccountRepository;
import com.my.bank.repository.TransactionRepository;
import com.my.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.my.bank.dto.enums.AccountStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.*;

@Service
public class BankAccountTransferRestService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<?> transferByPhone(CustomerDto sender) {
        return new ResponseEntity<>("", OK);
    }

    public ResponseEntity<?> transferByAccountNumber(CustomerDto sender, CardToCardResponseBody responseBody) {
        Optional<BankAccountDto> optSenderAccount = accountRepository.findByCardNumberAndStatus(responseBody.getSenderAccount(), ACCEPTED);
        Optional<BankAccountDto> optRecipientAccount = accountRepository.findByCardNumberAndStatus(responseBody.getRecipientAccount(), ACCEPTED);
        if (Objects.equals(responseBody.getRecipientAccount(), responseBody.getSenderAccount())) {
            return new ResponseEntity<>("Error", BAD_REQUEST);
        }
        if (optSenderAccount.isEmpty()) {
            return new ResponseEntity<>("Error", BAD_REQUEST);
        }
        if (optRecipientAccount.isEmpty()) {
            return new ResponseEntity<>("The recipient's account is specified incorrectly", NOT_FOUND);
        }
        BankAccountDto senderAccount = optSenderAccount.get();
        if (!Objects.equals(senderAccount.getCustomer().getCustomerId(), sender.getCustomerId())) {
            return new ResponseEntity<>("Access error", FORBIDDEN);
        }
        Double transferAmount = responseBody.getTransferAmount();
        Double senderBalance = senderAccount.getBalance();
        if (transferAmount > senderBalance) {
            return new ResponseEntity<>("Insufficient funds", PRECONDITION_FAILED);
        }
        senderAccount.setBalance(senderBalance - transferAmount);
        accountRepository.save(senderAccount);

        BankAccountDto recipientAccount = optRecipientAccount.get();
        Double recipientBalance = recipientAccount.getBalance();
        recipientAccount.setBalance(recipientBalance + transferAmount);
        accountRepository.save(recipientAccount);

        TransactionDto transaction = new TransactionDto(transferAmount, responseBody.getMessage(), recipientAccount, senderAccount);
        transactionRepository.save(transaction);

        return new ResponseEntity<>(OK);
    }
}

