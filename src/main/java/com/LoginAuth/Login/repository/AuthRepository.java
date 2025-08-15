package com.LoginAuth.Login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LoginAuth.Login.DTO.user;

public interface AuthRepository extends JpaRepository<user,Long> {
    Optional<user> findByEmail(String email);
}
