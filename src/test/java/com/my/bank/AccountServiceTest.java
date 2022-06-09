package com.my.bank;

import com.my.bank.dto.BankAccountDto;
import com.my.bank.dto.BankAccountOpeningQueueDto;
import com.my.bank.dto.CustomerDto;
import com.my.bank.repository.AccQueueRepository;
import com.my.bank.repository.AccountRepository;
import com.my.bank.repository.UserRepository;
import com.my.bank.service.BankAccountRestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private AccQueueRepository queueRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private BankAccountRestService bankAccountRestService;

    @Test
    @Repeat(value = 10)
    public void generateCardNumberTest() {
        List<Long> cardNumbers = accountRepository.findAll()
                .stream()
                .map(BankAccountDto::getCardNumber)
                .collect(Collectors.toList());
        BankAccountDto bankAccount = new BankAccountDto();
        bankAccount.createCard(cardNumbers, new CustomerDto());
        Assert.assertTrue(bankAccount.getSecurityCode() >= 100 && bankAccount.getSecurityCode() <= 999);
        assertEquals(16, String.valueOf(bankAccount.getCardNumber()).length());
    }

    @Test
    public void addToQueueTest() {
        CustomerDto customer = new CustomerDto();
        customer.setCustomerId(1L);
        customer.setPhoneNumber("+8888888888");
        userRepository.save(customer);

        ResponseEntity<?> responseEntityOk = bankAccountRestService.sendRequestToAddToQueue(customer);
        assertEquals(new ResponseEntity<>("The application has been sent for consideration", HttpStatus.OK), responseEntityOk);

        doReturn(Optional.of(BankAccountOpeningQueueDto.class))
                .when(queueRepository)
                .findByCustomer(customer);

        ResponseEntity<?> responseEntityReported = bankAccountRestService.sendRequestToAddToQueue(customer);
        assertEquals(new ResponseEntity<>("You are already in line to open a bank account", HttpStatus.ALREADY_REPORTED), responseEntityReported);
    }

}
