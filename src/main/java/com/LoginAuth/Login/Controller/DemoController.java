package com.LoginAuth.Login.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/api/v1/demo")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Olá! Você está autenticado e acessou um endpoint protegido!");
    }
}
