package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

//@NoArgsConstructor
@Getter
@Setter

public class Car {

    private Long id;
    private String model;
    private String producer;
    private int power;

    public Car() {
    }

    public Car(Long id, String model, String producer, int power) {
        this.id = id;
        this.model = model;
        this.producer = producer;
        this.power = power;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", producer='" + producer + '\'' +
                ", power=" + power +
                '}';
    }
}
