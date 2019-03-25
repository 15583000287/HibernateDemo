package com.example.jpademo.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = {"users"})
@Entity
@Table(name = "ACC_ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roleName;
    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();
}
