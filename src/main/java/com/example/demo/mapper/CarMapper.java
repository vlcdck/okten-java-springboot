package com.example.demo.mapper;

import com.example.demo.dto.CarDTO;
import com.example.demo.models.Car;

public class CarMapper {
    public static Car toEntity(CarDTO dto) {
        Car car = new Car();
        car.setId(dto.getId());
        car.setModel(dto.getModel());
        car.setProducer(dto.getProducer());
        car.setPower(dto.getPower());
        car.setPictureUrl(dto.getPictureUrl());
        return car;
    }

    public static CarDTO toDTO(Car car) {
        CarDTO dto = new CarDTO();
        dto.setId(car.getId());
        dto.setModel(car.getModel());
        dto.setProducer(car.getProducer());
        dto.setPower(car.getPower());
        dto.setPictureUrl(car.getPictureUrl());
        return dto;
    }
}
