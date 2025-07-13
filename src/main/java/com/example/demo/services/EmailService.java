package com.example.demo.services;

import com.example.demo.models.Car;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final String to = "pridatchenkovladislav@gmail.com";

    public void sendCarSavedEmail(Car car) {
        send("Авто збережено!", car);
    }

    public void sendCarDeletedEmail(Car car) {
        send("Авто видалено!", car);
    }

    private void send(String subject, Car car) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(car.toString());
        javaMailSender.send(mailMessage);
    }
}

