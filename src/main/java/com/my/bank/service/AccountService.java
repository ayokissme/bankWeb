package com.my.bank.service;

import com.my.bank.dto.AccountDto;
import com.my.bank.dto.CustomerDto;
import com.my.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void generateCardNumber(CustomerDto customerDto) {
        List<Long> cardNumbers = accountRepository.findAll()
                .stream()
                .map(AccountDto::getCardNumber)
                .collect(Collectors.toList());

        Random random = new Random();
        long generatedCardNumber;
        do {
            generatedCardNumber = getNumber(random);
        } while (cardNumbers.contains(generatedCardNumber));

        int csc = random.nextInt((999 - 100) + 1) + 100;

//        accountRepository.save(new AccountDto(customerDto, generatedCardNumber));
    }

    private Long getNumber(Random random) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return Long.parseLong(stringBuilder.toString());
    }
}
