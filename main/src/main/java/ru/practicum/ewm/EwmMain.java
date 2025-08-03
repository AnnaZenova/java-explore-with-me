package ru.practicum.ewm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.practicum", "ru.practicum.ewm"})
public class EwmMain {
    public static void main(String[] args) {
        SpringApplication.run(EwmMain.class);
    }
}