package cn.tedu.store.controller;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {
    @RequestMapping("/init.do")
    public String init(){
        System.out.println("init.do");
        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age",22);
        return "init";
        //return new ModelAndView("WEB-INFO/init.jsp",map);
    }
}
