package com.rca.rosinenzambaza.template.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rca.rosinenzambaza.template.v1.audits.Initializer;
import com.rca.rosinenzambaza.template.v1.enums.EGender;
import com.rca.rosinenzambaza.template.v1.enums.ERecordStatus;
import com.rca.rosinenzambaza.template.v1.enums.EVisibility;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class Person extends Initializer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "dob")
    private Date dob;

    private EGender gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "national_id")
    private String nationalId;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private EVisibility visibility = EVisibility.VISIBLE;

    private ERecordStatus status = ERecordStatus.ACTIVE;

    public Person(String firstName, String lastName, String email, Date dob, EGender gender, String phoneNumber, String nationalId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
    }
    //private User (After user creation)
}
