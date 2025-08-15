package com.LoginAuth.Login.DTO;

public class LoginResponseDTO {
    private String token;

    public void LoginResponseDTO(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }
}
