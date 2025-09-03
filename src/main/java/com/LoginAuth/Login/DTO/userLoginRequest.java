package com.LoginAuth.Login.DTO;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@Schema(description = "Dados necessários para login do usuário")
public class userLoginRequest {
    @NotBlank(message = "O email não pode ser vazio")
    @Email(message = "Formato inválido")
    @Schema(description = "Email do usuário", example = "usuario@exemplo.com", required = true)
    private String email;
    
    @NotBlank(message = "A senha não pode ser vazia")
    @Schema(description = "Senha do usuário", example = "minhasenha123", required = true)
    private String password;
}
