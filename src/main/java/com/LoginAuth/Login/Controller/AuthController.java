package com.LoginAuth.Login.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.LoginAuth.Login.DTO.userResponse;

@RestController
public class AuthController {
    @GetMapping("/ping")
    public String pong(){
        return "pong";
    }

    //@PostMapping("/auth/register")
    //public ResponseEntity<userResponse> 
}
