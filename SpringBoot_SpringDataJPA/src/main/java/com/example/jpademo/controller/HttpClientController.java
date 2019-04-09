package com.example.jpademo.controller;

import com.example.jpademo.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpClientController {
    @PostMapping("/getUser")
    public User getUser(@RequestBody User user){
        System.out.println("参数："+user);
        user.setId(1L);
        user.setUsername("wangxu");
        user.setPassword("123");
        user.setEmail("593464677@qq.com");
        return user;
    }
}
