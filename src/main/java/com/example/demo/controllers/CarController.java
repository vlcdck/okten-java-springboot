package com.example.demo.controllers;

import com.example.demo.models.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final List<Car> cars = new ArrayList<>();
    private Long idCounter = 0L;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = cars.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Car not found"));
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        car.setId(idCounter++);
        cars.add(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Car>> deleteCarById(@PathVariable Long id) {
        if (cars.stream().anyMatch(c -> c.getId().equals(id))) {
            cars.removeIf(c -> c.getId().equals(id));
            return new ResponseEntity<>(cars, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(cars, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/power/{value}")
    public ResponseEntity<List<Car>> getCarsByPower(@PathVariable Integer value) {
        List<Car> carsByPower = cars.stream()
                .filter(c -> c.getPower() == value)
                .toList();

        return new ResponseEntity<>(carsByPower, HttpStatus.OK);
    }

    @GetMapping("/producer/{value}")
    public ResponseEntity<List<Car>> getCarsByProducer(@PathVariable String value) {
        List<Car> carsByProducer = cars.stream()
                .filter(c -> c.getProducer().equals(value))
                .toList();
        return new ResponseEntity<>(carsByProducer, HttpStatus.OK);
    }

}
