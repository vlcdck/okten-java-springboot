package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("cars")
@Getter
@Setter
@ToString
public class Car {
    @Id
    private String id;
    private String model;
    private String producer;
    private int power;
    private String pictureUrl;
}
