package com.example.demo.dao;

import com.example.demo.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserDAO extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findAppUserByEmail(String email);
}
