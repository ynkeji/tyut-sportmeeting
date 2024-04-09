package com.tyut.sport;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
public class SportmeetingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportmeetingApplication.class, args);
    }

}
