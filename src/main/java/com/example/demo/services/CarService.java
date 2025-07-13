package com.example.demo.services;

import com.example.demo.dto.CarDTO;
import com.example.demo.mapper.CarMapper;
import com.example.demo.models.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllBytes;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final EmailService emailService;

    @Value("${upload.dir}")
    private String uploadDir;

    public CarService(CarRepository carRepository, EmailService emailService) {
        this.carRepository = carRepository;
        this.emailService = emailService;
    }

    public List<CarDTO> getAll() {
        return carRepository.findAll()
                .stream()
                .map(CarMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CarDTO getById(String id) {
        return CarMapper.toDTO(carRepository.findById(id).orElse(null));
    }

    public CarDTO create(CarDTO carDTO, MultipartFile file) throws IOException {
        String uploadDir = new File("uploads").getAbsolutePath();
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File dest = new File(uploadDir + File.separator + fileName);

        file.transferTo(dest);

        carDTO.setPictureUrl(fileName);
        Car car = carRepository.save(CarMapper.toEntity(carDTO));
        emailService.sendCarSavedEmail(car);
        return CarMapper.toDTO(car);
    }


    public boolean deleteById(String id) {
        return carRepository.findById(id).map(
                car -> {
                    carRepository.delete(car);
                    emailService.sendCarDeletedEmail(car);
                    return true;
                }
        ).orElse(false);
    }

    public long cleanupWeakCars() {
        List<Car> toDelete = carRepository.findAll().stream()
                .filter(c -> c.getPower() < 100)
                .collect(Collectors.toList());

        carRepository.deleteAll(toDelete);
        return toDelete.size();
    }

    public byte[] getPhotoByCarId(String id) throws IOException {
        Car car = carRepository.findById(id).orElse(null);
        if (car == null || car.getPictureUrl() == null) {
            return null;
        }
        File file = new File(uploadDir + File.separator + car.getPictureUrl());
        if (!file.exists()) {
            return null;
        }
        return readAllBytes(file.toPath());
    }


}
