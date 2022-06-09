package com.my.bank;

import com.my.bank.config.PasswordConfig;
import com.my.bank.dto.CustomerDto;
import com.my.bank.dto.enums.Country;
import com.my.bank.dto.enums.UserGender;
import com.my.bank.dto.enums.UserRole;
import com.my.bank.repository.UserRepository;
import com.my.bank.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static com.my.bank.dto.enums.UserRole.USER;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordConfig passwordConfig;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void saveUserTest() {
        CustomerDto customer = new CustomerDto();
        customer.setPhoneNumber("89514002161");
        customer.setPassword(passwordConfig.bCryptPasswordEncoder().encode("1234"));

        boolean customerCreated = userService.isCustomerCreated(customer);

        assertTrue(customerCreated);
        assertTrue(CoreMatchers.is(customer.getRoles()).matches(Collections.singleton(USER)));

        verify(userRepository, Mockito.times(1)).save(customer);
    }

    @Test
    public void userExistsTest() {
        CustomerDto customer = new CustomerDto();
        customer.setPhoneNumber("+7777777777");
        customer.setPassword(passwordConfig.bCryptPasswordEncoder().encode("1234"));

        doReturn(Optional.of(CustomerDto.class))
                .when(userRepository)
                .findByPhoneNumber("+7777777777");

        boolean customerCreated = userService.isCustomerCreated(customer);

        assertFalse(customerCreated);
    }

}
