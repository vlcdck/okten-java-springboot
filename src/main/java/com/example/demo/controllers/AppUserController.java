package com.example.demo.controllers;

import com.example.demo.dao.AppUserDAO;
import com.example.demo.mappers.AppUserMapper;
import com.example.demo.models.dto.AppUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class AppUserController {

    private final AppUserMapper appUserMapper;
    private final AppUserDAO appUserDAO;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public ResponseEntity<List<AppUserDTO>> getAllAppUsers() {
        List<AppUserDTO> users = appUserDAO.findAll()
                .stream()
                .map(appUserMapper::toAppUserDTO)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public ResponseEntity<AppUserDTO> getAppUserById(@PathVariable Integer id) {
        return appUserDAO.findById(id)
                .map(appUserMapper::toAppUserDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteAppUserById(@PathVariable Integer id) {
        if (!appUserDAO.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        appUserDAO.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
