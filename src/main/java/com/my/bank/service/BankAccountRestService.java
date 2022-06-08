package com.my.bank.service;

import com.my.bank.dto.BankAccountOpeningQueueDto;
import com.my.bank.dto.CustomerDto;
import com.my.bank.repository.AccQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BankAccountRestService {

    @Autowired
    private AccQueueRepository queueRepository;

    public ResponseEntity<?> sendResponseToAddToQueue(CustomerDto customer) {
        if (queueRepository.findByCustomer(customer).isPresent()) {
            return new ResponseEntity<>("You are already in line to open a bank account", HttpStatus.ALREADY_REPORTED);
        }
        queueRepository.save(new BankAccountOpeningQueueDto(customer));
        return new ResponseEntity<>("The application has been sent for consideration", HttpStatus.OK);
    }

    public ResponseEntity<?> sendResponseToDeleteFromQueue(BankAccountOpeningQueueDto accInQueue) {
        queueRepository.delete(accInQueue);
        return new ResponseEntity<>("The account has been deleted", HttpStatus.OK);
    }
}
