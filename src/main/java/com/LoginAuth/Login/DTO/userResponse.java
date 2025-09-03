package com.LoginAuth.Login.DTO;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Dados do usuário retornados após registro")
public class userResponse {
    @Schema(description = "ID único do usuário", example = "1")
    private Long user_id;
    
    @Schema(description = "Email do usuário", example = "usuario@exemplo.com")
    private String email;
}
