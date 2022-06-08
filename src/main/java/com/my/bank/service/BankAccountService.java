package com.my.bank.service;

import com.my.bank.dto.BankAccountDto;
import com.my.bank.dto.CustomerDto;
import com.my.bank.repository.AccQueueRepository;
import com.my.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

    public void createOpeningAccountRequest(CustomerDto customerDto) {
        BankAccountDto accountDto = generateAccountRequest();
        accountDto.setCustomerDto(customerDto);
        accountRepository.save(accountDto);
    }

    public BankAccountDto generateAccountRequest() {
        List<Long> cardNumbers = accountRepository.findAll()
                .stream()
                .map(BankAccountDto::getCardNumber)
                .collect(Collectors.toList());

        Random random = new Random();
        long generatedCardNumber;
        do {
            generatedCardNumber = getNumber(random);
        } while (cardNumbers.contains(generatedCardNumber));
        int csc = random.nextInt((999 - 100) + 1) + 100;

        return new BankAccountDto(generatedCardNumber, csc);
    }

    private Long getNumber(Random random) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        if (stringBuilder.toString().startsWith("0")) {
            stringBuilder.replace(0, 1, String.valueOf((int) ((Math.random() * 8) + 1)));
        }
        return Long.parseLong(stringBuilder.toString());
    }
}
