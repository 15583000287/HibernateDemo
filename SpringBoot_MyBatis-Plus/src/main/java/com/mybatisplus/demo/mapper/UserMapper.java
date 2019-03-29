package com.mybatisplus.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.demo.base.SupperMapper;
import com.mybatisplus.demo.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends SupperMapper<User> {
    //自定义接口方法
    @Select("selectUserList")  //xml文件中的id <select id="selectUserList" resultType="User">
    List<User> selectUserList(Page<User> page, String state);

    int deleteById2(Long id);
}
