package com.example.demo.services;

import com.example.demo.dao.AppUserDAO;
import com.example.demo.models.*;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private PasswordEncoder passwordEncoder;
    private AppUserDAO appUserDAO;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        AppUser appUser = AppUser.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();
        String jwtToken = jwtService.generateToken(appUser);
        String refreshToken = jwtService.generateRefreshToken(appUser);
        appUser.setRefreshToken(refreshToken);

        appUserDAO.save(appUser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        AppUser appUser = appUserDAO.findAppUserByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(authenticationRequest.getEmail()));
        String jwtToken = jwtService.generateToken(appUser);
        String refreshToken = jwtService.generateRefreshToken(appUser);
        appUser.setRefreshToken(refreshToken);
        appUserDAO.save(appUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(jwtToken)
                .build();
    }

    public AuthenticationResponse refresh(RefreshRequest refreshRequest) {
        String token = refreshRequest.getRefreshToken();
        String username = jwtService.extractUsername(token);
        AppUser appUser = appUserDAO.findAppUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        String newAccesToken = null;
        String newRefreshToken = null;
        if (appUser.getRefreshToken().equals(token)) {
            newAccesToken = jwtService.generateToken(appUser);
            newRefreshToken = jwtService.generateRefreshToken(appUser);
            appUser.setRefreshToken(newRefreshToken);
            appUserDAO.save(appUser);
        }
        return AuthenticationResponse.builder()
                .token(newAccesToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}
