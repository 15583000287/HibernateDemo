package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonDemo {

    @Test
    public void stringToJson(){
        //复杂格式json字符串
        String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";
        JSONObject json = (JSONObject) JSON.parse(COMPLEX_JSON_STR);

        System.out.println(json.get("teacherAge"));
        Set<String> keySet = json.keySet();
        for(String key: keySet){
                System.out.println(key+" :"+json.get(key));
        }
    }
}
