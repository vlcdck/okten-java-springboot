package com.example.demo.controllers;

import com.example.demo.dto.CarDTO;
import com.example.demo.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public List<CarDTO> findAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> findById(@PathVariable String id) {
        CarDTO dto = carService.getById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CarDTO> save(@RequestParam String model,
                                       @RequestParam String producer,
                                       @RequestParam int power,
                                       @RequestParam MultipartFile photo) throws IOException {

        CarDTO dto = new CarDTO();
        dto.setModel(model);
        dto.setProducer(producer);
        dto.setPower(power);
        return ResponseEntity.ok(carService.create(dto, photo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return carService.deleteById(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/clean/weak")
    public ResponseEntity<String> clean() {
        long deleted = carService.cleanupWeakCars();
        return ResponseEntity.ok().body("Deleted " + deleted + " cars with power less then 100");
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> getPhoto(@PathVariable String id) throws IOException {
        byte[] image = carService.getPhotoByCarId(id);
        if (image == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header("Content-Type", "image/jpeg")
                .body(image);
    }


}
