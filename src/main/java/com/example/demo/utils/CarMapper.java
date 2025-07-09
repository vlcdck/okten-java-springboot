package com.example.demo.utils;

import com.example.demo.dto.CarLevel1DTO;
import com.example.demo.dto.CarLevel2DTO;
import com.example.demo.dto.CarLevel3DTO;
import com.example.demo.models.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {
    public static CarLevel1DTO toLevel1DTO(Car car) {
        return new CarLevel1DTO(car.getId(), car.getModel(), car.getProducer(), car.getPower());
    }

    public static CarLevel2DTO toLevel2DTO(Car car) {
        return new CarLevel2DTO(car.getModel(), car.getProducer(), car.getPower());
    }

    public static CarLevel3DTO toLevel3DTO(Car car) {
        return new CarLevel3DTO(car.getModel(), car.getProducer());
    }

    public static List<CarLevel1DTO> toLevel1DTOList(List<Car> cars) {
        return cars.stream().map(CarMapper::toLevel1DTO).collect(Collectors.toList());
    }

    public static List<CarLevel2DTO> toLevel2DTOList(List<Car> cars) {
        return cars.stream().map(CarMapper::toLevel2DTO).collect(Collectors.toList());
    }

    public static List<CarLevel3DTO> toLevel3DTOList(List<Car> cars) {
        return cars.stream().map(CarMapper::toLevel3DTO).collect(Collectors.toList());
    }
}
