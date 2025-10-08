package com.danrvro.velasquezbacked;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VelasquezbackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(VelasquezbackendApplication.class, args);
    }
}
