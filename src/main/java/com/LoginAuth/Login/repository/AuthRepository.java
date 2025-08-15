package com.LoginAuth.Login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LoginAuth.Login.DTO.user;

public interface AuthRepository extends JpaRepository<user,Long> {
    
}
