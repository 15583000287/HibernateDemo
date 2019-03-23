package com.example.jpademo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 商户网站审核记录
 */
@Data
@DynamicUpdate //跟新数据时只会跟新发生改变的值
@Entity
@Table(name = "TSL_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String salt;
    private String email;
    /**用于Dto*/
    @Transient
    private Date startDate;
    @Transient
    private Date endDate;
    @Transient
    private String order;
    @Transient
    private String[] orderBy;
}
