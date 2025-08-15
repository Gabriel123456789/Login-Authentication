package com.LoginAuth.Login.service;

import org.springframework.stereotype.Service;

import com.LoginAuth.Login.DTO.userLoginRequest;
import com.LoginAuth.Login.DTO.userRegister;
import com.LoginAuth.Login.DTO.userResponse;

public interface AuthService {
    public userResponse registerUser(userRegister registrationDTO);
    public String login(userLoginRequest loginRequest);
}
