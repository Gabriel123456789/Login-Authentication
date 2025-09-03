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

import jakarta.validation.Valid;

// OpenAPI/SpringDoc annotations
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Authentication", description = "Endpoints para registro e login de usuários")
public class AuthController {
    @Autowired
    private AuthService authService;
    
    @GetMapping("/ping")
    @Operation(summary = "Health Check", description = "Endpoint para verificar se a API está funcionando")
    @ApiResponse(responseCode = "200", description = "API está funcionando")
    public String pong(){
        return "pong";
    }

    @PostMapping("/auth/register")
    @Operation(summary = "Registrar usuário", description = "Cria uma nova conta de usuário no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
        @ApiResponse(responseCode = "409", description = "Usuário já existe")
    })
    public ResponseEntity<userResponse> cadastraUser(@Valid @RequestBody userRegister registroDTO){
        userResponse createdUser = authService.registerUser(registroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/auth/login")
    @Operation(summary = "Login do usuário", description = "Autentica um usuário e retorna um token JWT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
        @ApiResponse(responseCode = "400", description = "Dados de login inválidos")
    })
    public ResponseEntity<LoginResponseDTO> verificaUser(@Valid @RequestBody userLoginRequest loginRequest){
        String token = authService.login(loginRequest);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    
}
