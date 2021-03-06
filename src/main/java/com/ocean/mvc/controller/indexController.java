package com.ocean.mvc.controller;

import cn.chendahai.dingding.service.DingdingService;
import com.ocean.config.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个controller没什么用处，就当是测试了
 */
@Controller
public class indexController {

    @Autowired
    private DingdingService dingdingService;

    @Value("${myhost}")
    public String myhost;

    @RequestMapping({"/index.do"})
    @ResponseBody
    public Object index() {
        System.out.println("chy");
        return dingdingService.send("hello world");
    }


    @RequestMapping("testControllerAdvice1.do")
    @ResponseBody
    public String testControllerAdvice1(@ModelAttribute("author") String author) {
        System.out.println("author:" + author);
        int i = 1 / 0;
        return "hello";
    }

    @RequestMapping("testControllerAdvice2.do")
    public String testControllerAdvice2(ModelMap map) {
        System.out.println("author:" + map.get("author"));
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new MyException(500l, map.get("author").toString());
        }
        return "world";
    }

    /*
    http://localhost:8081/testFreemarker
     */
    @RequestMapping("testFreemarker")
    public String index(ModelMap modelMap, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "dahai");
        modelMap.put("map", map);
        modelMap.put("city", "北京");
        return "index";
    }

    @RequestMapping("/handsontable/demo1.do")
    public String demo1() {
        System.out.println("demo1");
        return "handsontable/demo1";
    }

    @RequestMapping("/angular/test1.do")
    public String test1() {
        return "angular/test1";
    }

}
