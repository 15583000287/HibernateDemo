package com.example.jpademo.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * Created by ZXing
 * QQ:1490570560
 * 解决Hibernate自动建表中文乱码问题
 */
public class MysqlConfig extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
