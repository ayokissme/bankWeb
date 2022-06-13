package com.my.bank.restController;

import com.my.bank.dto.responseBody.TransferResponseBody;
import com.my.bank.dto.CustomerDto;
import com.my.bank.service.BankAccountTransferRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/account/transfer")
public class BankAccountTransferRestController {

    @Autowired
    private BankAccountTransferRestService restService;

    @PatchMapping("/phone")
    public ResponseEntity<?> transferMoneyByPhone(@RequestBody TransferResponseBody responseBody,
                                                  @AuthenticationPrincipal CustomerDto sender) {
        return restService.transfer(sender, responseBody);
    }

    @PatchMapping("/card-to-card")
    public ResponseEntity<?> transferMoneyCardToCard(@RequestBody TransferResponseBody responseBody,
                                                     @AuthenticationPrincipal CustomerDto sender) {
        return restService.transfer(sender, responseBody);
    }
}
