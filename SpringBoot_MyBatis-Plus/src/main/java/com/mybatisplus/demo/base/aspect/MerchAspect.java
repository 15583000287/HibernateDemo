package com.mybatisplus.demo.base.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
public class MerchAspect {
    public static long startTime;
    public static long endTime;

    @Around("execution(* com.mybatisplus.demo.controller.*.*(..))")
    @Pointcut("execution(public * com.mybatisplus.demo.controller.UserController.*(..))")
    public void print(){
        System.out.println("切点签名 print");
    }

    /*@Before注解表示在具体的方法之前执行*/
    @Before("print()")
    public void before(JoinPoint joinPoint) {
        log.info("前置切面before……");
        startTime = System.currentTimeMillis();
    }

    /*@After注解表示在方法执行之后执行*/
    @After("print()")
    public void after() {
        log.info("后置切面after……");
        endTime = System.currentTimeMillis()-startTime;
    }

    /*@AfterReturning注解用于获取方法的返回值*/
    @AfterReturning(pointcut = "print()", returning = "object")
    public void getAfterReturn(Object object) {
        log.info("本次接口耗时={}ms", endTime); //日志输出{}占位符
        log.info("afterReturning={}", object.toString());
    }

}
