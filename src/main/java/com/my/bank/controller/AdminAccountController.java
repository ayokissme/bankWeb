package com.my.bank.controller;

import com.my.bank.service.AdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminAccountController {

    @Autowired
    private AdminAccountService adminService;

    @GetMapping("/accounts")
    public ModelAndView displayAccountResponses() {
        return adminService.getAccountResponses();
    }
}
