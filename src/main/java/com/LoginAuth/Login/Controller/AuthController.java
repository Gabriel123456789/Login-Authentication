package com.LoginAuth.Login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.LoginAuth.Login.DTO.LoginResponseDTO;
import com.LoginAuth.Login.DTO.userLoginRequest;
import com.LoginAuth.Login.DTO.userRegister;
import com.LoginAuth.Login.DTO.userResponse;
import com.LoginAuth.Login.service.AuthService;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;
    
    @GetMapping("/ping")
    public String pong(){
        return "pong";
    }

    @PostMapping("/auth/register")
    public ResponseEntity<userResponse> cadastraUser(@RequestBody userRegister registroDTO){
        userResponse createdUser = authService.registerUser(registroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDTO> verificaUser(@RequestBody userLoginRequest loginRequest){
        String token = authService.login(loginRequest);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    
}
