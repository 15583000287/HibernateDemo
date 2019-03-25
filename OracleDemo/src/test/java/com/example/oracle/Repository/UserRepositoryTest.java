package com.example.oracle.Repository;

import com.example.oracle.entity.User;
import com.example.oracle.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTest(){
        User user = new User();
        user.setUsername("wangxu");
        user.setPassword("123");
        userRepository.save(user);
    }

    @Test
    public void getTest(){
        System.out.println(userRepository.findById(1));
       // System.out.println(userRepository.getOne(1));
    }
}
