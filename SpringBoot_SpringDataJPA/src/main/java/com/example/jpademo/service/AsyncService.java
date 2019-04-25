package com.example.jpademo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步测试
 */
@Service
public class AsyncService {

    /**
     * 加了@Async注解，表示一个异步方法
     * 执行此方法时，会重新开辟一条线程，与其他线程同时执行
     * 注意：一个类中调用本类中的异步方法，貌似不起作用
     * @throws Exception
     */
    @Async
    public void outNum() throws Exception{
        System.out.println("2");
        Thread.sleep(4000);
        System.out.println("3");
    }

}
