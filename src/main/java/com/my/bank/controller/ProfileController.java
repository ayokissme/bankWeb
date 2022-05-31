package com.my.bank.controller;

import com.my.bank.dto.CustomerDto;
import com.my.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String profile(@AuthenticationPrincipal CustomerDto customer) {
        return "profile/profile";
    }
}
