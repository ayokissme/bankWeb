package com.my.bank.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {

    @Id
    @GeneratedValue
    private Long transactionId;

    @Column
    private Double transferAmount;

    @Column
    private String message;

    @OneToOne
    @JoinColumn(name = "recipient_id")
    private BankAccountDto recipientAccount;

    @OneToOne
    @JoinColumn(name = "sender_id")
    private BankAccountDto senderAccount;

    public TransactionDto(Double transferAmount, String message, BankAccountDto recipientAccount, BankAccountDto senderAccount) {
        this.transferAmount = transferAmount;
        this.message = message;
        this.recipientAccount = recipientAccount;
        this.senderAccount = senderAccount;
    }
}
