package com.LoginAuth.Login.DTO;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="hash_password")
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna as permissões/papéis do usuário. Por enquanto, pode ser uma lista vazia.
        // Veremos isso na parte de AUTORIZAÇÃO.
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        // IMPORTANTE: O "username" para o Spring Security será o nosso e-mail.
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    // Eles servem para desabilitar contas, bloquear, etc.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
