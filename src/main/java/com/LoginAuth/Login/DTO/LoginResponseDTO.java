package com.LoginAuth.Login.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta do endpoint de login contendo o token JWT")
public class LoginResponseDTO {
    @Schema(description = "Token JWT para autenticação", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }
}
