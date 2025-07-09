package com.example.demo.controllers;

import com.example.demo.dto.CarLevel1DTO;
import com.example.demo.dto.CarLevel2DTO;
import com.example.demo.models.Car;
import com.example.demo.services.CarService;
import com.example.demo.utils.CarMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarLevel1DTO>> getAllCars() {
        return new ResponseEntity<>(CarMapper
                .toLevel1DTOList(
                        carService.getAllCars()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarLevel1DTO> getCarById(@PathVariable Long id) {
        return new ResponseEntity<>(CarMapper
                .toLevel1DTO(
                        carService.getCarById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarLevel1DTO> addCar(@RequestBody @Valid Car car) {
        return new ResponseEntity<>(CarMapper
                .toLevel1DTO(
                        carService.addCar(car)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/power/{value}")
    public ResponseEntity<List<CarLevel2DTO>> getCarsByPower(@PathVariable Integer value) {
        return new ResponseEntity<>(CarMapper
                .toLevel2DTOList(
                        carService
                                .getCarsByPower(value)),
                HttpStatus.OK);
    }

    @GetMapping("/producer/{value}")
    public ResponseEntity<List<CarLevel2DTO>> getCarsByProducer(@PathVariable String value) {

        return new ResponseEntity<>(CarMapper
                .toLevel2DTOList(
                        carService.getCarsByProducer(value)
                ), HttpStatus.OK);
    }

}
