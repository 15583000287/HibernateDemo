package com.example.jpademo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {

    private String id;
    private String name;
    private List<String> list;

}
