package com.rca.rosinenzambaza.template.v1.models;

import com.rca.rosinenzambaza.template.v1.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private float balance;
    private float amount;

    @OneToOne(mappedBy = "account")
    private Save save;

    @OneToOne(mappedBy = "account")
    private Withdraw withdraw;

    private  String message ;
    private UUID messageId;
}
