package com.my.bank.dto;

import com.my.bank.dto.enums.AccountStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

import static com.my.bank.dto.enums.AccountStatus.UNDER_CONSIDERATION;
import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "bank_account")
@Getter
@Setter
@NoArgsConstructor
public class BankAccountDto {

    @Id
    @GeneratedValue
    private Long accountId;

    @Column(unique = true)
    private Long cardNumber;

    @Column
    private int securityCode;

    @Column
    private Double balance;

    @Column
    @Enumerated(STRING)
    private AccountStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerDto customer;

    public void createCard(List<Long> cardNumbers, CustomerDto customer) {
        long generatedCardNumber;
        Random random = new Random();
        do {
            generatedCardNumber = Long.parseLong(getCardNumber(random));
        } while (cardNumbers.contains(generatedCardNumber));

        this.cardNumber = generatedCardNumber;
        this.securityCode = random.nextInt((999 - 100) + 1) + 100;
        this.customer = customer;
        this.status = UNDER_CONSIDERATION;
    }

    private String getCardNumber(Random random) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int randNumb = random.nextInt(10);
            if (i == 0 && randNumb == 0) randNumb = (int) ((Math.random() * 8) + 1);
            stringBuilder.append(randNumb);
        }
        return stringBuilder.toString();
    }
}
