package com.myy.demo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.myy.demo.config.RedisService;
import com.myy.demo.entity.User;
import com.myy.demo.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.activation.DataHandler;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
@Slf4j
public class TestIO {
    @Autowired
    private RedisService redisService;
    @RequestMapping("/1")
    @ResponseBody
    public String test1(){
        try{
            FileInputStream fileInputStream=new FileInputStream(new File("C:\\Users\\minxd\\Desktop\\main.txt"));
            FileOutputStream fileOutputStream=new FileOutputStream(new File("C:\\Users\\minxd\\Desktop\\main1.txt"));
            byte [] bt=new byte[1024];
            int byread;
            if((byread=fileInputStream.read(bt))>0){
                fileOutputStream.write(bt,0,byread);
            }
        }catch (Exception e){
            log.error(e.toString());
        }
        return  "zz";
    }

    @RequestMapping("/2")
    @ResponseBody
    public String test2(){
        //redisService.add("s","hello world");
        redisService.hmSet("haha","hehe","ss");
        redisService.hmSet("haha","hehe1","xx");
        redisService.hmSet("haha","hehe2","kk");
        User u=new User();
        u.setId("1");
        u.setPassword("pwd");
        u.setUserName("username");
        String userStr=JacksonUtil.objToJson(u);
        List<User> list=new ArrayList<User>();
        User u1=new User("2","hh","ll");
        list.add(u);
        list.add(u1);
        redisService.hmSet("haha","list",JacksonUtil.objToJson(list));
        redisService.hmSet("haha","user",userStr);
        List<User> l1= JacksonUtil.jsonToObj((String)redisService.hmGet("haha","list"),new TypeReference<ArrayList<User>>(){});

         return  "zz";

    }
}
