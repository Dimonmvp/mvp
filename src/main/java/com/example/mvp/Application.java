package com.example.mvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//Аннотация которая запускает ищет контроллеры и тд
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
