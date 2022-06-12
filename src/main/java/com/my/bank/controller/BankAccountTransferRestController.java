package com.my.bank.controller;

import com.my.bank.controller.ajaxResponseBody.CardToCardResponseBody;
import com.my.bank.dto.CustomerDto;
import com.my.bank.service.BankAccountTransferRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/account/transfer")
public class BankAccountTransferRestController {

    @Autowired
    private BankAccountTransferRestService restService;

    @PatchMapping("/phone")
    public ResponseEntity<?> transferMoneyByPhone(
            @AuthenticationPrincipal CustomerDto sender) {
        return restService.transferByPhone(sender);
    }

    @PatchMapping("/card-to-card")
    public ResponseEntity<?> transferMoneyCardToCard(@RequestBody CardToCardResponseBody responseBody,
                                                     @AuthenticationPrincipal CustomerDto sender) {
        return restService.transferByAccountNumber(sender, responseBody);
    }
}
