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
@RequestMapping("account")
public class BankAccountController {

    @Autowired
    private BankAccountService accountService;

    @GetMapping
    public ModelAndView accountOpening(@AuthenticationPrincipal CustomerDto customer) {
        return accountService.getPageModel(customer);
    }
}
