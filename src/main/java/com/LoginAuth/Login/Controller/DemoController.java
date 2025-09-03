package com.LoginAuth.Login.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Tag(name = "Demo", description = "Endpoints protegidos que requerem autenticação")
public class DemoController {
    @GetMapping("/api/v1/demo")
    @Operation(
        summary = "Endpoint protegido", 
        description = "Endpoint de demonstração que requer autenticação JWT",
        security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponse(responseCode = "200", description = "Acesso autorizado")
    @ApiResponse(responseCode = "401", description = "Token inválido ou ausente")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Olá! Você está autenticado e acessou um endpoint protegido!");
    }
}
