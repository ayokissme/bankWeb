package com.my.bank.controller.ajaxResponseBody;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardToCardResponseBody {
    private Double transferAmount;
    private String message;
    private Long recipientAccount;
    private Long senderAccount;
}
