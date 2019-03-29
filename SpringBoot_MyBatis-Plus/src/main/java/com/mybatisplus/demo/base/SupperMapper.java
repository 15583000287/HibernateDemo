package com.mybatisplus.demo.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 自定义Mapper基类
 * 这个接口需要放到mapper目录外，不能让mybatis scanner 扫描到。
 * @param <T> 实体类
 */
public interface SupperMapper<T> extends BaseMapper<T> {
}
