package com.mybatisplus.demo.controller;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.demo.service.IUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/page")
    public Object selectPage(Model model) {

        Page page = new Page(1, 10);
        page = userService.selectUserPage(page, "NORMAL");
        return page;
    }

    @RequestMapping("/hello")
    public String hello() {
        log.info("调用接口");
        return "hello";
    }
}
