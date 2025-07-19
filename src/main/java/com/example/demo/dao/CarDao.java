package com.example.demo.dao;

import com.example.demo.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarDao extends JpaRepository<Car, Integer> {
    List<Car> findByOwnerEmail(String email);
}
