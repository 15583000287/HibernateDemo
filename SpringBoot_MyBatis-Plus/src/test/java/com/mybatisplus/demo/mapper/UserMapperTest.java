package com.mybatisplus.demo.mapper;

import com.mybatisplus.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.SoundbankResource;
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
        int row = userMapper.insert(user);
        System.out.println(row);
    }

    @Test
    public void selectTest(){
        List<User> users = userMapper.selectList(null);
        for(User user: users){
            System.out.println(user);
        }
    }

    @Test
    public void deleteByIdTest(){
       userMapper.deleteById(1);
    }
}
