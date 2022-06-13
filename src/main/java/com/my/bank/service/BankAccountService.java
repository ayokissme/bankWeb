package com.my.bank.service;

import com.my.bank.dto.BankAccountDto;
import com.my.bank.dto.CustomerDto;
import com.my.bank.dto.TransactionDto;
import com.my.bank.repository.AccQueueRepository;
import com.my.bank.repository.AccountRepository;
import com.my.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

import static com.my.bank.dto.enums.AccountStatus.ACCEPTED;

@Service
public class BankAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

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

    public ModelAndView getHistory(CustomerDto customer, BankAccountDto bankAccount) {
        ModelAndView mav = new ModelAndView();
        if (!Objects.equals(bankAccount.getCustomer().getCustomerId(), customer.getCustomerId())) {
            mav.setViewName("error/access-denied");
            return mav;
        }
        List<TransactionDto> transactions = transactionRepository.findTransactions(bankAccount);
        mav.addObject("transactions", transactions);
        mav.setViewName("account/account-transactions");
        return mav;
    }
}
