package com.my.bank.controller.responseBody;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneResponseBody {
    private Double transferAmount;
    private String message;
    private String recipientPhoneNumber;
    private Long senderAccount;
    private int securityCode;
}
