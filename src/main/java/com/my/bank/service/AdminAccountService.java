package com.my.bank.service;

import com.my.bank.dto.BankAccountOpeningQueueDto;
import com.my.bank.repository.AccQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class AdminAccountService {

    @Autowired
    private AccQueueRepository queueRepository;

    public ModelAndView getAccountResponses() {
        List<BankAccountOpeningQueueDto> allResponses = queueRepository.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("responses", allResponses);
        mav.setViewName("account/admin-accounts");
        return mav;
    }
}
