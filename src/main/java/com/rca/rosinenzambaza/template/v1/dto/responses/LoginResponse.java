package com.rca.rosinenzambaza.template.v1.dto.responses;

import com.rca.rosinenzambaza.template.v1.models.Role;
import com.rca.rosinenzambaza.template.v1.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {

    public String token;
    public User userData;
    private Set<Role> userRoles;
}