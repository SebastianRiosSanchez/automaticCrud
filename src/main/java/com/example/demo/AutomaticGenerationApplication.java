package com.example.demo;

import com.example.demo.auto.CrudGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;


@SpringBootApplication
public class AutomaticGenerationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomaticGenerationApplication.class, args);

    }


}
