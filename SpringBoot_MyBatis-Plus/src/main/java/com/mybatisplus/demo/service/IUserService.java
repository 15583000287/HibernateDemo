package com.mybatisplus.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.demo.entity.User;

public interface IUserService{
    Page<User> selectUserPage(Page<User> page, String state);
}
