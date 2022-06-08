package com.my.bank.controller;

import com.my.bank.dto.BankAccountOpeningQueueDto;
import com.my.bank.dto.CustomerDto;
import com.my.bank.service.BankAccountRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BankAccountRestController {

    @Autowired
    private BankAccountRestService bankAccountRestService;

    @PostMapping("/public/account/create")
    public ResponseEntity<?> addToBankAccountQueue(@AuthenticationPrincipal CustomerDto customerDto) {
        return bankAccountRestService.sendResponseToAddToQueue(customerDto);
    }

    @DeleteMapping("/private/account/delete-from-queue")
    public ResponseEntity<?> deleteFromBankAccountQueue(BankAccountOpeningQueueDto accInQueue) {
        return bankAccountRestService.sendResponseToDeleteFromQueue(accInQueue);
    }
}
