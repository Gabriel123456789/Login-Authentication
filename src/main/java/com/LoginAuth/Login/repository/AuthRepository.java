package com.LoginAuth.Login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LoginAuth.Login.DTO.User;

public interface AuthRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
