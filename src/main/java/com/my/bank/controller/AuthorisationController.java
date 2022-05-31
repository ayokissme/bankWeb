package com.my.bank.controller;

import com.my.bank.dto.CustomerDto;
import com.my.bank.exceptions.UserAlreadyExistException;
import com.my.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthorisationController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String login() {
        if (userService.isLoggedIn()) {
            return "profile/profile";
        }
        return "redirect:";
    }

    @GetMapping("registration")
    public String registration(@ModelAttribute("customer") CustomerDto customer, Model model) {
        if (userService.isLoggedIn()) {
            return "profile/profile";
        }
        return "redirect:";
    }

    @PostMapping("registration")
    public String registerUser(@Valid @ModelAttribute("customer") CustomerDto customer,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "authorisation/registration";
        }

        try {
            return userService.registerNewCustomerAccount(customer);
        } catch (UserAlreadyExistException e) {
            model.addAttribute("message", "An account for that phone number already exists.");
            return "authorisation/registration";
        }
    }

}
