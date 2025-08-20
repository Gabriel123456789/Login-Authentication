package com.LoginAuth.Login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.LoginAuth.Login.DTO.User;
import com.LoginAuth.Login.DTO.userLoginRequest;
import com.LoginAuth.Login.DTO.userRegister;
import com.LoginAuth.Login.DTO.userResponse;
import com.LoginAuth.Login.repository.AuthRepository;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public userResponse registerUser(userRegister registrationDTO){
        //Conferir se existe o email
        String emailUser = registrationDTO.getEmail();
    Optional<User> emailExistente = authRepository.findByEmail(emailUser);
        if(emailExistente.isPresent()){
            throw new RuntimeException("Erro: E-mail já cadastrado.");
        }
        else{
            User newUser = new User();
            newUser.setEmail(emailUser);
            String hashedPassword = passwordEncoder.encode(registrationDTO.getPassword());
            newUser.setPassword(hashedPassword);
            User savedUser = authRepository.save(newUser);
            userResponse response = new userResponse();
            response.setEmail(savedUser.getEmail());
            response.setUser_id(savedUser.getId());
            return response;
        }
    }

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtservice;


    @Override
    public String login(userLoginRequest loginRequest){
        var usernamePassword = new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(),
            loginRequest.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(usernamePassword);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtservice.generateToken(userDetails);
        return jwtToken;
    }


}
