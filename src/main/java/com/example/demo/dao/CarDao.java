package com.example.demo.dao;

import com.example.demo.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDao extends JpaRepository<Car, Long> {
    List<Car> getCarsByPower(Integer value);

    List<Car> getCarsByProducer(String producer);
}
