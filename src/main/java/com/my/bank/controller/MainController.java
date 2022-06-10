package com.my.bank.controller;

import com.my.bank.dto.CustomerDto;
import com.my.bank.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping
    public ModelAndView mainPage(@AuthenticationPrincipal CustomerDto customerDto, Model model) {
        return mainService.getMainPageMaV(customerDto);
    }

}
