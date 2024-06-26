package com.rca.rosinenzambaza.template.v1.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class CreateSaveDTO {
    @NotNull
    private UUID customerId;
    @NotNull
    private UUID accountId;
    @NotNull
    private float amount;
    @NotNull
    private Date bankingTime ;
}
