package com.example.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private List<SimpleCarDTO> cars;
}
