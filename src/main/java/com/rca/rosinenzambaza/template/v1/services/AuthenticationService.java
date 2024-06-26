package com.rca.rosinenzambaza.template.v1.services;


import com.rca.rosinenzambaza.template.v1.dto.requests.LoginDTO;
import com.rca.rosinenzambaza.template.v1.dto.responses.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginDTO dto);

    // other methods

}
