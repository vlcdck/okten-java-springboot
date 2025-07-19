package com.example.demo.mappers;

import com.example.demo.models.AppUser;
import com.example.demo.models.Car;
import com.example.demo.models.dto.CarDTO;
import com.example.demo.models.dto.SimpleCarDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarMapper {

    private AppUserMapper appUserMapper;

    public CarDTO toCarDTO(Car car) {
        if (car == null) {
            return null;
        }
        return new CarDTO(
                car.getBrand(),
                car.getModel(),
                car.getPrice(),
                appUserMapper.toAppUserShortDTO(car.getOwner())
        );
    }

    public SimpleCarDTO toSimpleCarDTO(Car car) {
        if (car == null) {
            return null;
        }
        return new SimpleCarDTO(
                car.getBrand(),
                car.getModel(),
                car.getPrice()
        );
    }

    public Car fromCarDTO(CarDTO carDTO, AppUser owner) {
        if (carDTO == null) {
            return null;
        }
        return Car.builder()
                .brand(carDTO.getBrand())
                .model(carDTO.getModel())
                .price(carDTO.getPrice())
                .owner(owner)
                .build();
    }

}
