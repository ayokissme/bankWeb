package com.my.bank.controller;

import com.my.bank.dto.CustomerDto;
import com.my.bank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/account")
public class BankAccountController {

    @Autowired
    private BankAccountService accountService;

    @GetMapping
    public ModelAndView getAccounts(@AuthenticationPrincipal CustomerDto customer) {
        return accountService.getAccountsMaV(customer);
    }

    @GetMapping("/open")
    public ModelAndView accountOpening(@AuthenticationPrincipal CustomerDto customer) {
        return accountService.getOpeningPageModel(customer);
    }

    @GetMapping("/transfer/phone")
    public String getTransferByPhone(@AuthenticationPrincipal CustomerDto customer) {
        return "account/account-transfer-by-phone";
    }

    @GetMapping("/transfer/card-to-card")
    public String getTransferByCard(@AuthenticationPrincipal CustomerDto customer) {
        return "account/account-transfer-by-card";
    }
}
