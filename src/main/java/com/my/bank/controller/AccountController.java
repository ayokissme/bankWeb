package com.my.bank.controller;

import com.my.bank.dto.CustomerDto;
import com.my.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    private String accountOpening(@AuthenticationPrincipal CustomerDto customer) {
        accountService.generateCardNumber(customer);
        return "account/account";
    }
}
