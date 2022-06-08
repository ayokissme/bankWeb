package com.my.bank.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bank_account")
@Getter
@Setter
@NoArgsConstructor
public class BankAccountDto {

    public BankAccountDto(long cardNumber, int securityCode) {
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
    }

    @Id
    @GeneratedValue
    private Long accountId;

    @Column(unique = true)
    private Long cardNumber;

    @Column
    private int securityCode;

    @Column
    private boolean isEnabled;

    @Column
    private Date openingDate;

    @Column
    private Date endingDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerDto customerDto;
}
