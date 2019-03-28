package com.mybatisplus.demo.service.impl;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.demo.service.IUserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private IUserService userService;

    @RequestMapping("/page")
    public Object selectPage(Model model) {

        Page page = new Page(1, 10);
        page = userService.selectUserPage(page, "NORMAL");
        return page;
    }
}