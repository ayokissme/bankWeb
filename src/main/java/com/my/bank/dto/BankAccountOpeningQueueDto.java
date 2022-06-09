package com.my.bank.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "acc_opening_queue")
@Getter
@Setter
@NoArgsConstructor
public class BankAccountOpeningQueueDto {

    @Id
    @GeneratedValue
    private Long queueId;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerDto customer;

    @OneToOne
    @JoinColumn(name = "account_id")
    private BankAccountDto bankAccount;

    public BankAccountOpeningQueueDto(CustomerDto customer, BankAccountDto bankAccount) {
        this.customer = customer;
        this.bankAccount = bankAccount;
    }
}
