package com.my.bank.controller;

import com.my.bank.dto.BankAccountDto;
import com.my.bank.dto.BankAccountOpeningQueueDto;
import com.my.bank.dto.CustomerDto;
import com.my.bank.service.BankAccountRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankAccountRestController {

    @Autowired
    private BankAccountRestService bankAccountRestService;

    @GetMapping(value = "/public/account/get", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<BankAccountDto> getBankAccounts(@AuthenticationPrincipal CustomerDto customer) {
        return bankAccountRestService.getBankAccounts(customer);
    }

    @PostMapping("/public/account/create")
    public ResponseEntity<?> addToBankAccountQueue(@AuthenticationPrincipal CustomerDto customerDto) {
        return bankAccountRestService.sendRequestToAddToQueue(customerDto);
    }

    @DeleteMapping("/private/account/delete-from-queue")
    public ResponseEntity<?> deleteFromBankAccountQueue(BankAccountOpeningQueueDto accInQueue) {
        return bankAccountRestService.sendRequestToDeleteFromQueue(accInQueue);
    }

    @PatchMapping("/private/account/accept")
    public ResponseEntity<?> acceptRequestToCreateBankAccount(@RequestParam(name = "queueId") BankAccountOpeningQueueDto accInQueue) {
        return bankAccountRestService.acceptRequestForOpeningAccount(accInQueue);
    }

    @DeleteMapping("/private/account/reject")
    public ResponseEntity<?> rejectRequestToCreateBankAccount(@RequestParam(name = "queueId") BankAccountOpeningQueueDto accInQueue) {
        return bankAccountRestService.rejectRequestForOpeningAccount(accInQueue);
    }
}
