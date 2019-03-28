package com.mybatisplus.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "user")
public class User implements Serializable {

//    @TableId(type = IdType.UUID) //指定主键生成何种类型
    private Long id;
    private String name;
    private Integer age;
    @TableField(select = false) //此字段不查询  value:字段名
    private String email;
}
