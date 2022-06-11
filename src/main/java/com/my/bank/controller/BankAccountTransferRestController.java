package com.my.bank.controller;

import com.my.bank.dto.BankAccountDto;
import com.my.bank.dto.CustomerDto;
import com.my.bank.service.BankAccountTransferRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/public/account/transfer")
public class BankAccountTransferRestController {

    @Autowired
    private BankAccountTransferRestService restService;

    @PatchMapping("/from/{senderAccount}/by/phone/{phoneNumber}/{amount}")
    public ResponseEntity<?> transferMoneyByPhone(@PathVariable("senderAccount") BankAccountDto bankAccountDto,
                                                  @PathVariable("phoneNumber") String recipientPhoneNumber,
                                                  @PathVariable("amount") Double amount,
                                                  @AuthenticationPrincipal CustomerDto sender) {
        return restService.transferByPhone(recipientPhoneNumber, sender, bankAccountDto, amount);
    }

    @PatchMapping("/from/{senderAccount}/by/account/{accountNumber}/{amount}")
    public ResponseEntity<?> transferMoneyByAccountNumber(@PathVariable("senderAccount") BankAccountDto bankAccountDto,
                                                          @PathVariable("accountNumber") Long recipientAccountNumber,
                                                          @PathVariable("amount") Double amount,
                                                          @AuthenticationPrincipal CustomerDto sender) {
        return restService.transferByAccountNumber(recipientAccountNumber, sender, bankAccountDto, amount);
    }
}
