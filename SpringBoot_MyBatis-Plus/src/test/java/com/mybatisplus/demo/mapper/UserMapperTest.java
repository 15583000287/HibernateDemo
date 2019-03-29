package com.mybatisplus.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.demo.entity.User;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void addTest(){
        User user = new User();
        user.setName("jack");
        user.setAge(22);
        user.setEmail("593464677@qq.com");
        user.setCreateTime(new Date());
        int row = userMapper.insert(user);
        System.out.println(row);
    }

    @Test
    public void selectTest(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name","jackc");
        List<User> users = userMapper.selectList(queryWrapper);
        for(User user: users){
            System.out.println(user);
        }
    }

    @Test
    public void deleteByIdTest(){
        List<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(7);
        userMapper.deleteBatchIds(list);
    }

    @Test
    public void selectPageTest(){
        Page<User> page = new Page(1,3,true);
        userMapper.selectPage(page,null);
        System.out.println(page.getCurrent());
        System.out.println(page.getRecords());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
    }
}
