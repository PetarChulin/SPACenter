package com.example.spacenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class SpaCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpaCenterApplication.class, args);
    }


}
