package com.rca.rosinenzambaza.template.v1.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.ldap.PagedResultsControl;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity


public class Message {

    @Id
    private UUID id;
    private  UUID customerId ;
    private  String message  ;
    private Date datetime ;

}
