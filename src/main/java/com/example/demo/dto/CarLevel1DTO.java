package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarLevel1DTO {
    private Long id;
    private String model;
    private String producer;
    private int power;
}
