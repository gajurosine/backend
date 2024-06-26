package com.rca.rosinenzambaza.template.v1.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Component

public class CreateAccountDTO {
    @NotNull
    private String accountNumber;
    @NotNull
    private String accountType ;
    @NotNull
    private UUID customerId;
    @NotNull
    private float balance;
    @NotNull
    private float amount ;
}
