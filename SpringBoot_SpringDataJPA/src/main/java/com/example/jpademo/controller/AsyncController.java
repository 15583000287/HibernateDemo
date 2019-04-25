package com.example.jpademo.controller;

import com.example.jpademo.dto.User;
import com.example.jpademo.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.SoundbankResource;

@RestController
public class AsyncController {
    @Autowired
    private AsyncService asyncService;

    @GetMapping("/index")
    public String index() throws Exception{
        System.out.println("1");
        asyncService.outNum();
        Thread.sleep(100);
        System.out.println("4");
        return "index";
    }

    @PostMapping("/test")
    public User requestTset(@RequestBody User user/*,@RequestParam("age") String age*/){  //发送post请求时，url也可携带参数，但是不建议
        System.out.println(user);
        User u = new User();
        u.setId(user.getId());
        u.setName(user.getName());
        u.setList(user.getList());
        return u;
    }
}
