package com.my.bank.service;

import com.my.bank.dto.CustomerDto;
import com.my.bank.repository.AccQueueRepository;
import com.my.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class BankAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccQueueRepository queueRepository;

    public ModelAndView getPageModel(CustomerDto customer) {
        ModelAndView mav = new ModelAndView();
        boolean isCustomerInQueue = queueRepository.findByCustomer(customer).isPresent();
        mav.addObject("isInQueue", isCustomerInQueue);
        mav.setViewName("account/account");
        return mav;
    }
}
