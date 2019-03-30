package com.mybatisplus.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.mybatisplus.demo.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
//@KeySequence(value = "user_sequence")  (Oracle方式)
@TableName(value = "user")
public class User extends BaseEntity {
    private static final long serialVersionUID = 5067511945025215179L;
    //    @TableId(type = IdType.UUID) //指定主键生成何种类型
//    @TableId(value = "ID",type = IdType.INPUT)   (Oracle方式)
    private Long id;
    private String name;
    private Integer age;
    @TableField(select = false) //此字段不查询  value:字段名
    private String email;
}
