package com.LoginAuth.Login.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Dados necessários para registro de novo usuário")
public class userRegister {
    @NotBlank(message = "O email não pode ser vazio")
    @Email(message = "Formato inválido")
    @Schema(description = "Email do usuário", example = "usuario@exemplo.com", required = true)
    private String email;

    @NotBlank(message = "A senha não pode ser vazia.")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
    @Schema(description = "Senha do usuário (mínimo 8 caracteres)", example = "minhasenha123", minLength = 8, required = true)
    private String password;
}
