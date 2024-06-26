package com.rca.rosinenzambaza.template.v1.serviceImpls;

import com.rca.rosinenzambaza.template.v1.dto.requests.LoginDTO;
import com.rca.rosinenzambaza.template.v1.dto.responses.LoginResponse;
import com.rca.rosinenzambaza.template.v1.enums.EUserStatus;
import com.rca.rosinenzambaza.template.v1.exceptions.BadRequestException;
import com.rca.rosinenzambaza.template.v1.models.User;
import com.rca.rosinenzambaza.template.v1.repositories.UserRepository;
import com.rca.rosinenzambaza.template.v1.security.jwt.JwtUtils;
import com.rca.rosinenzambaza.template.v1.security.user.UserAuthority;
import com.rca.rosinenzambaza.template.v1.security.user.UserSecurityDetails;
import com.rca.rosinenzambaza.template.v1.security.user.UserSecurityDetailsService;
import com.rca.rosinenzambaza.template.v1.services.AuthenticationService;
import com.rca.rosinenzambaza.template.v1.services.UserService;
import com.rca.rosinenzambaza.template.v1.utils.ExceptionUtils;
import com.rca.rosinenzambaza.template.v1.utils.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserSecurityDetailsService userSecurityDetailsService;
    private final UserService userService;

    private final JwtUtils jwtUtils;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, UserSecurityDetailsService userSecurityDetailsService, UserService userService, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.userSecurityDetailsService = userSecurityDetailsService;
        this.userService = userService;

        this.jwtUtils = jwtUtils;


    }

    @Override
    public LoginResponse login(LoginDTO dto) {
        try {
            User user = userRepository.findUserByEmail(dto.getEmail()).orElseThrow(() -> new BadRequestException("User with provided email not found"));
            if (Hash.isTheSame(dto.getPassword(), user.getPassword())) {
                if (user.getAccountStatus().equals(EUserStatus.ACTIVE)) {
                    UserSecurityDetails userSecurityDetails = (UserSecurityDetails) userSecurityDetailsService.loadUserByUsername(dto.getEmail());
                    System.out.println("The token: " + userSecurityDetails.getGrantedAuthorities());
                    List<GrantedAuthority> grantedAuthorities = userSecurityDetails.getGrantedAuthorities();
                    if (grantedAuthorities.isEmpty()) {
                        throw new BadRequestException("User has no role");
                    }
                    UserAuthority userAuthority = (UserAuthority) grantedAuthorities.get(0);
                    String role = userAuthority.getAuthority();
                    String token = jwtUtils.createToken(user.getId(), dto.getEmail(), role);
                    return new LoginResponse(token, user, user.getRoles());
                }else {
                    throw new BadRequestException("The account is not activated");
                }
            } else {
                throw new BadRequestException("Incorrect Email or Password");
            }
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
//            e.printStackTrace();
            return null;
        }
    }














}

