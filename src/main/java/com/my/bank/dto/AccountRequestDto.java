package com.my.bank.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "account_request")
@Getter
@Setter
public class AccountRequestDto {

    @Id
    @GeneratedValue
    private Long id;

}
