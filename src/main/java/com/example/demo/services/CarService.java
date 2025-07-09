package com.example.demo.services;

import com.example.demo.dao.CarDao;
import com.example.demo.models.Car;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarDao carDao;

    public List<Car> getAllCars() {
        return carDao.findAll();
    }

    public Car getCarById(Long id) {
        return carDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found: " + id));
    }

    public Car addCar(Car car) {
        return carDao.save(car);
    }

    public void deleteCar(Long id) {
        if (!carDao.existsById(id)) {
            throw new EntityNotFoundException("Car not found: " + id);
        }
        carDao.deleteById(id);
    }

    public List<Car> getCarsByPower(Integer value) {
        return carDao.getCarsByPower(value);
    }

    public List<Car> getCarsByProducer(String value) {
        return carDao.getCarsByProducer(value);
    }
}
