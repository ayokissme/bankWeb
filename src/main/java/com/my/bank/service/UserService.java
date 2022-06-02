package com.my.bank.service;

import com.my.bank.config.PasswordConfig;
import com.my.bank.dto.CustomerDto;
import com.my.bank.exceptions.UserAlreadyExistException;
import com.my.bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.my.bank.dto.enums.UserRole.ADMIN;
import static com.my.bank.dto.enums.UserRole.USER;

@Service
@AllArgsConstructor
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordConfig passwordConfig;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(phone)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Пользователь с такой почтой не найден"));
    }

    @Override
    public String registerNewCustomerAccount(CustomerDto customer) throws UserAlreadyExistException {
        if (isCustomerCreated(customer)) {
            throw new UserAlreadyExistException("User with such phone already exists");
        }
        return "redirect:login";
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().contains(ADMIN) || authentication.getAuthorities().contains(USER);
    }

    public boolean isCustomerCreated(CustomerDto customer) {
        if (phoneExists(customer.getPhoneNumber())) {
            return false;
        }

        String password = passwordConfig.bCryptPasswordEncoder().encode(customer.getPassword());
        customer.setPassword(password);
        customer.setRoles(Collections.singleton(USER));
        userRepository.save(customer);
        return true;
    }

    private boolean phoneExists(String phone) {
        return userRepository.findByPhoneNumber(phone).isPresent();
    }
}
