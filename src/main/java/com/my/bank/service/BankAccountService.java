package com.my.bank.service;

import com.my.bank.dto.BankAccountDto;
import com.my.bank.dto.CustomerDto;
import com.my.bank.repository.AccQueueRepository;
import com.my.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static com.my.bank.dto.enums.AccountStatus.ACCEPTED;

@Service
public class BankAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccQueueRepository queueRepository;

    public ModelAndView getOpeningPageModel(CustomerDto customer) {
        ModelAndView mav = new ModelAndView();
        boolean isCustomerInQueue = queueRepository.findByCustomer(customer).isPresent();
        mav.addObject("isInQueue", isCustomerInQueue);
        mav.setViewName("account/account-open");
        return mav;
    }

    public ModelAndView getAccountsMaV(CustomerDto customer) {
        ModelAndView mav = new ModelAndView();
        List<BankAccountDto> accounts = accountRepository.findAllByCustomerAndStatus(customer, ACCEPTED);
        mav.addObject("accounts", accounts);
        mav.setViewName("account/account-main");
        return mav;
    }

    public ModelAndView transferPageByPhone(CustomerDto customer) {
        ModelAndView mav = new ModelAndView();
        return mav;
    }

    public ModelAndView transferPageByCard(CustomerDto customer) {
        ModelAndView mav = new ModelAndView();
        return mav;
    }
}
