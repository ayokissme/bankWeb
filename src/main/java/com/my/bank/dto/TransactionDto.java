package com.my.bank.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class TransactionDto {

    @Id
    @GeneratedValue
    private Long transactionId;

    @Column
    private Double transferAmount = 0.0;

    @Column
    private String message;

    @OneToOne
    @JoinColumn(name = "recipient_id")
    private BankAccountDto recipientAccount;

    @OneToOne
    @JoinColumn(name = "sender_id")
    private BankAccountDto senderAccount;

}
