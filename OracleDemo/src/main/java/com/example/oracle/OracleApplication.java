package com.example.oracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.oracle.repository")
public class OracleApplication {

    public static void main(String[] args) {
        SpringApplication.run(OracleApplication.class, args);
    }

}
