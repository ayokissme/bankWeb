package com.my.bank.controller;

import com.my.bank.dto.CustomerDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TransferRestController {

    @PatchMapping
    public ModelAndView transferMoney(@RequestParam CustomerDto recipient,
                                      @AuthenticationPrincipal CustomerDto sender) {
        return new ModelAndView();
    }
}
