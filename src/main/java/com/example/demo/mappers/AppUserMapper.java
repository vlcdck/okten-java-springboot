package com.example.demo.mappers;

import com.example.demo.models.AppUser;
import com.example.demo.models.dto.AppUserDTO;
import com.example.demo.models.dto.AppUserShortDTO;
import com.example.demo.models.dto.SimpleCarDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AppUserMapper {
    private final CarMapper carMapper;

    public AppUserDTO toAppUserDTO(AppUser appUser) {
        if (appUser == null) {
            return null;
        }

        List<SimpleCarDTO> cars = appUser.getCars()
                .stream()
                .map(carMapper::toSimpleCarDTO)
                .toList();
        return new AppUserDTO(
                appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getEmail(),
                cars
        );
    }

    public AppUserShortDTO toAppUserShortDTO(AppUser appUser) {
        if (appUser == null) {
            return null;
        }

        return new AppUserShortDTO(
                appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getEmail()
        );
    }


}

