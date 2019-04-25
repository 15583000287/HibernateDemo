package com.example.jpademo.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

//@Data
//@ToString(exclude = {"role"})
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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", role=" + role +
                '}';
    }
}
