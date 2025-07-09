package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarLevel2DTO {
    private String model;
    private String producer;
    private int power;
}
