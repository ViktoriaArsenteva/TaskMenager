package com.example.taskme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskme.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
    
}
