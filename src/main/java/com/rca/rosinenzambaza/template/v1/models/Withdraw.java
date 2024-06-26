package com.rca.rosinenzambaza.template.v1.models;

import com.rca.rosinenzambaza.template.v1.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Withdraw {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private float amount;
    private AccountType accountType = AccountType.withDraw;
    private Date bankingTime;
}
