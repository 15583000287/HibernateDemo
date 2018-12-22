package cn.tedu.demo_1.controller;

import cn.tedu.demo_1.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public  String  hello(){
        System.out.print("进来了！");
        return "Hello Spring Boot !";
    }

    @GetMapping("/zero")
    public Result<Void> zero(){
        System.out.print(3/0);
        return new Result<Void>(1,"success");
    }
}
