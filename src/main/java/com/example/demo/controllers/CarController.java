package com.example.demo.controllers;

import com.example.demo.dao.AppUserDAO;
import com.example.demo.dao.CarDao;
import com.example.demo.mappers.CarMapper;
import com.example.demo.models.AppUser;
import com.example.demo.models.Car;
import com.example.demo.models.dto.CarDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/cars")
@AllArgsConstructor
public class CarController {
    private final CarMapper carMapper;
    private final CarDao carDao;
    private final AppUserDAO appUserDao;

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public ResponseEntity<List<CarDTO>> getCars() {
        List<CarDTO> carsDTO = carDao.findAll()
                .stream()
                .map(carMapper::toCarDTO)
                .toList();
        return ResponseEntity.ok(carsDTO);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO, Principal principal) {
        AppUser owner = appUserDao.findAppUserByEmail(principal.getName())
                .orElseThrow();

        Car car = carMapper.fromCarDTO(carDTO, owner);
        Car saved = carDao.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(carMapper.toCarDTO(saved));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer id) {
        if (!carDao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        carDao.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
