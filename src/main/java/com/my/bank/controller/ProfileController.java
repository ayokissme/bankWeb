package com.my.bank.controller;

import com.my.bank.dto.CustomerDto;
import com.my.bank.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ModelAndView profile(@AuthenticationPrincipal CustomerDto customer) {
        return profileService.getProfile(customer);
    }
}
