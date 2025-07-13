package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO {
    private String id;
    private String model;
    private String producer;
    private int power;
    private String pictureUrl;
}
