package com.example.jpademo;

import com.example.jpademo.service.impl.MerchWebAuditServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpademoApplication.class)
public class JpademoApplicationTests {
    @Autowired
    private MerchWebAuditServiceImpl merchWebAuditService;

    @Test
    public void findById() {
        System.out.println(merchWebAuditService.findByid(1L));
        System.out.println(merchWebAuditService.findByid(1L));
    }

}
