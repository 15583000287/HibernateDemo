package com.mybatisplus.demo.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis Plus配置类
 *
 * Spring Boot 使用事务非常简单，首先使用注解 @EnableTransactionManagement 开启事务支持后，
 * 然后在访问数据库的Service方法上添加注解 @Transactional 便可。
 */
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@Configuration
public class MyBatisPlusConfiguration {

    /**
     * 分页插件,注册了分页拦截器才能实现分页
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    /**
     * 注入sql注入器(逻辑删除注册Bean）
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }


    /***
     * SQL执行效率插件【生产环境可以关闭】
     * plus 的性能优化
     * @return
     */
    @Bean
    //@Profile({"dev","test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
        performanceInterceptor.setMaxTime(1000);
        /*<!--SQL是否格式化 默认false-->*/
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }





}
