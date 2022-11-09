package com.demo.hateos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.PagedResourcesAssembler;

@SpringBootApplication
public class HateosApplication {

    public static void main(String[] args) {
        SpringApplication.run(HateosApplication.class, args);
    }

}
