package com.example.jpademo.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Data
@ToString(exclude = {"role"})
@Entity
@Table(name = "ACC_USER")
@Proxy(lazy = false)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String age;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")  //维护外键
    private Role role;
}
