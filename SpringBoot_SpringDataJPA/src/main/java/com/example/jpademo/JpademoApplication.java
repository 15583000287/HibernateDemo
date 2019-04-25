package com.example.jpademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync//开启异步
@EnableDiscoveryClient
@SpringBootApplication
public class JpademoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpademoApplication.class, args);
    }

}
