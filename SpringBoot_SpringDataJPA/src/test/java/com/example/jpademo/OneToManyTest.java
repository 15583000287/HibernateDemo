package com.example.jpademo;

import com.example.jpademo.entity.Role;
import com.example.jpademo.entity.User;
import com.example.jpademo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 一对多关联关系测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpademoApplication.class)
public class OneToManyTest {
    @Autowired
    private UserRepository userRepository;

    /**
     * 一对多 add
     */
    @Test
    public void saveTest(){
        //创建用户
        User user = new User();
        user.setName("张三");
        user.setAge("22");

        //创建角色
        Role role = new Role();
        role.setRoleName("管理员");

        //关联
        role.getUsers().add(user);
        user.setRole(role);

        //保存
        this.userRepository.save(user);
    }

    /**
     *一对多关联查询
     */
    @Test
    public void findTest(){
        User user = userRepository.getOne(1L);
        System.out.println(user);
        Role role = user.getRole();
        System.out.println(role);
    }
}
