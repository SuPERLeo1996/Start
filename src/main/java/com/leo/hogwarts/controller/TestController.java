package com.leo.hogwarts.controller;

import com.leo.hogwarts.service.Impl.TestServiceImpl;
import com.leo.hogwarts.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName TestController
 * @Description
 * @Author Leo
 * @Date 2020/3/27 15:39
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestServiceImpl testService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MongoTemplate template;

    @RequestMapping(value = "/aop",method = RequestMethod.GET)
    public Object aopTest(String param,Integer size){
        return "success";
    }

    @RequestMapping(value = "/sql",method = RequestMethod.GET)
    public Object sqlTest(){
        return testService.test();
    }

    @RequestMapping(value = "/redis",method = RequestMethod.GET)
    public Object redisTest(){
        return redisUtil.hgetAll("DB:JZJTCS");
    }

    @RequestMapping(value = "/mongoDB",method = RequestMethod.GET)
    public Object mongoDBTest(){
        Query query = new Query();
        return template.find(query,Map.class,"guestOrder");
    }

}
