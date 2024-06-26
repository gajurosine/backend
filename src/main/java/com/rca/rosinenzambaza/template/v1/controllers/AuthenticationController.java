package com.rca.rosinenzambaza.template.v1.controllers;

import com.rca.rosinenzambaza.template.v1.dto.requests.LoginDTO;
import com.rca.rosinenzambaza.template.v1.dto.responses.LoginResponse;
import com.rca.rosinenzambaza.template.v1.payload.ApiResponse;
import com.rca.rosinenzambaza.template.v1.services.AuthenticationService;
import com.rca.rosinenzambaza.template.v1.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final String ENTITY_NAME = "Authentication";

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDTO loginDTO) {
        try {
            log.info("Request to login user {}", loginDTO.getEmail());
            // Call the login service method
            LoginResponse loginResponse = authenticationService.login(loginDTO);
            // Return a successful response
            return new ResponseEntity<>(new ApiResponse(
                    true,
                    "Login successful",
                    loginResponse
            ), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while logging in user {}", loginDTO.getEmail(), e);
            // Handle exceptions and return an appropriate response
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }




    // Add other methods for reset password, resend verification code, reset password, etc.
    // Example:





    // Add other methods for reset password, resend verification code, reset password, etc.
    // Example:



    // resend invitation code


}
