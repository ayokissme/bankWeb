package com.my.bank.service;

import com.my.bank.dto.BankAccountDto;
import com.my.bank.dto.CustomerDto;
import com.my.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class MainService {

    @Autowired
    private AccountRepository accountRepository;

    public ModelAndView getMainPageMaV(CustomerDto customer) {
        ModelAndView mav = new ModelAndView();
        List<BankAccountDto> accounts = accountRepository.findAllByCustomer(customer);
        mav.addObject("accounts", accounts);
        mav.setViewName("main-page");
        return mav;
    }
}
