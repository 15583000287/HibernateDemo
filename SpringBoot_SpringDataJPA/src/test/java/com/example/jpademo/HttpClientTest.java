package com.example.jpademo;

import com.alibaba.fastjson.JSON;
import com.example.jpademo.entity.User;
import com.example.jpademo.service.impl.MerchWebAuditServiceImpl;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpademoApplication.class)
public class HttpClientTest {
    /**
     * 测试使用HttpClient发送Post请求    application/json 参数类型
     */
    @Test
    public void getUser() {
        String url = "http://localhost:8081/getUser";
        //将对象转成Json字符串(Map也可转换成json字符串)
        User user = new User();
        user.setId(1L);
        user.setUsername("王旭");
        String json = JSON.toJSONString(user);
        //第一步：创建HttpClient对象
        HttpClient httpClient = HttpClients.createDefault();
        //第二步：创建httpPost对象
        HttpPost httpPost = new HttpPost(url);
        //第三步：给httpPost设置JSON格式的参数
        StringEntity requestEntity = new StringEntity(json,"utf-8");
        requestEntity.setContentEncoding("UTF-8");

        httpPost.setHeader("Content-type", "application/json");
        httpPost.setEntity(requestEntity);

        //第四步：发送HttpPost请求，获取返回值
        String returnValue = "这是默认返回值，接口调用失败";
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            returnValue = httpClient.execute(httpPost,responseHandler);
            System.out.println(returnValue);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                ((CloseableHttpClient) httpClient).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    @Test
    public void objToJson(){
        User user = new User();
        user.setId(1L);
        user.setUsername("王旭");
        user.setPassword("123");
        String str = JSON.toJSONString(user);
        System.out.println("转换后："+str);
    }

}
