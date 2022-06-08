package com.my.bank;

import com.my.bank.dto.BankAccountDto;
import com.my.bank.service.BankAccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private BankAccountService bankAccountService;

    @Test
    @Repeat(value = 10)
    public void generateCardNumberTest() {
        BankAccountDto accountDto = bankAccountService.generateAccountRequest();
        Assert.assertTrue(accountDto.getSecurityCode() >= 100 && accountDto.getSecurityCode() <= 999);
        Assert.assertEquals(16, String.valueOf(accountDto.getCardNumber()).length());
    }

}
