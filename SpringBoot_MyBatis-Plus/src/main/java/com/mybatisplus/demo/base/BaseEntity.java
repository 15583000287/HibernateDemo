package com.mybatisplus.demo.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体基类
 */
@Data
public class BaseEntity implements Serializable {
    /**
     * 创建时间
     */
    @TableField(select = true)
    private Date createTime;
    /**
     * 逻辑表示是否删除  删除：1   未删除: 0
     */
    @TableLogic
    private Integer isDelete;
}
