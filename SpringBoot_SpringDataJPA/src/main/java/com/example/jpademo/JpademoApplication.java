package com.example.jpademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

import java.util.Scanner;

@EnableCaching //开启缓存
@SpringBootApplication
public class JpademoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(JpademoApplication.class, args);
        // 读取控制台输入的端口，避免端口冲突
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入端口:");
        String port = scan.nextLine();
        new SpringApplicationBuilder(JpademoApplication.class).properties( "server.port=" + port).run(args);
    }

}
