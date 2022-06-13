package com.my.bank.dto.responseBody;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferResponseBody {
    private Double transferAmount;
    private String message;
    private Long recipientAccount;
    private Long senderAccount;
    private int securityCode;
}
