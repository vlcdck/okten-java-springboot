package com.example.demo.controllers;

import com.example.demo.dao.CarDao;
import com.example.demo.models.Car;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {

    private final CarDao carDao;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carDao.findAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return new ResponseEntity<>(carDao.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        return new ResponseEntity<>(carDao.save(car), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Car>> deleteCarById(@PathVariable Long id) {
        carDao.deleteById(id);
        return new ResponseEntity<>(carDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/power/{value}")
    public ResponseEntity<List<Car>> getCarsByPower(@PathVariable Integer value) {
        List<Car> carsByPower = carDao.getCarsByPower(value);
        return new ResponseEntity<>(carsByPower, HttpStatus.OK);
    }

    @GetMapping("/producer/{value}")
    public ResponseEntity<List<Car>> getCarsByProducer(@PathVariable String value) {
        List<Car> carsByProducer = carDao.getCarsByProducer(value);
        return new ResponseEntity<>(carsByProducer, HttpStatus.OK);
    }

}
