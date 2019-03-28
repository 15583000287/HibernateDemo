package com.mybatisplus.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatisplus.demo.entity.User;
import com.mybatisplus.demo.mapper.UserMapper;
import com.mybatisplus.demo.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService{
    @Override
    public Page<User> selectUserPage(Page<User> page, String state) {
        page.setRecords(baseMapper.selectUserList(page,state));
        return page;
    }
}
