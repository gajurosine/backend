package com.rca.rosinenzambaza.template.v1.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private Date dob;
    @OneToOne(mappedBy = "customer")
    @JoinColumn(name = "customer_id")
    private Save save;
    private float balance;
    private Date  lastUpdateDateTime;
}