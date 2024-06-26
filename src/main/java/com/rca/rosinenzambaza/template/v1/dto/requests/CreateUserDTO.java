package com.rca.rosinenzambaza.template.v1.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CreateUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private UUID roleId;
    private  String password ;
}

