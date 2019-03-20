package com.example.jpademo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * SpringBoot文件上传   默认上传文件大小为10MB,若需上传更大文件需要在application.properties文件配置
 */
@RestController //表示该类下的方法的返回值会自动做json格式转换
public class FileUploadController {
    @RequestMapping("/fileUploadController")
    public Map<String,Object> fileUpload(MultipartFile filename)throws Exception{
        System.out.println(filename.getOriginalFilename());
        filename.transferTo(new File("F:/"+filename.getOriginalFilename()));
        Map<String,Object> map = new HashMap<>();
        map.put("msg","ok");
        return map;
    }
}
