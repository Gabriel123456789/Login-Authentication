package com.LoginAuth.Login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.LoginAuth.Login.DTO.user;
import com.LoginAuth.Login.DTO.userLoginRequest;
import com.LoginAuth.Login.DTO.userRegister;
import com.LoginAuth.Login.DTO.userResponse;
import com.LoginAuth.Login.repository.AuthRepository;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthRepository authRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public userResponse registerUser(userRegister registrationDTO){
        //Conferir se existe o email
        String emailUser = registrationDTO.getEmail();
        Optional<user> emailExistente = authRepository.findByEmail(emailUser);
        if(emailExistente.isPresent()){
            throw new RuntimeException("Erro: E-mail já cadastrado.");
        }
        else{
            user newUser = new user();
            newUser.setEmail(emailUser);
            String hashedPassword = passwordEncoder.encode(registrationDTO.getPassword());
            newUser.setPassword(hashedPassword);
            user savedUser = authRepository.save(newUser);
            userResponse response = new userResponse();
            response.setEmail(savedUser.getEmail());
            response.setUser_id(savedUser.getId());
            return response;
        }
    }

    @Autowired
    private AuthenticationManager authenticationManager;
    private JwtService jwtservice;


    @Override
    public String login(userLoginRequest loginRequest){
        
    }


}
