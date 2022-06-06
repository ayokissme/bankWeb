package com.my.bank.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account")
@Getter
@Setter
public class AccountDto {

    @Id
    @GeneratedValue
    private Long accountId;

    @Column(unique = true)
    private Long cardNumber;

    @Column
    private int securityCode;

    @Column
    private boolean accountAccess;

    @Column
    private Date openingDate;

    @Column
    private Date endingDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerDto customerDto;

}
