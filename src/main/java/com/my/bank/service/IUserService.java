package com.my.bank.service;

import com.my.bank.dto.CustomerDto;
import com.my.bank.exceptions.UserAlreadyExistsException;

public interface IUserService {
    String registerNewCustomerAccount(CustomerDto userDTO) throws UserAlreadyExistsException;
}
