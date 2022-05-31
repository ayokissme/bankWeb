package com.my.bank.controller;

import com.my.bank.dto.CustomerDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String mainPage(@AuthenticationPrincipal CustomerDto customerDto, Model model) {
        model.addAttribute("customer", customerDto);
        return "main-page";
    }

}
