package com.my.bank.service;

import com.my.bank.dto.CustomerDto;
import com.my.bank.exceptions.UserAlreadyExistException;

public interface IUserService {
    String registerNewCustomerAccount(CustomerDto userDTO) throws UserAlreadyExistException;
}
