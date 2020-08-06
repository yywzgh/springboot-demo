package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.UserService;

@RestController
public class HelloController {

    @Autowired
    private UserService userService;
 
	
    @RequestMapping("/hello")
    @ResponseBody
    public String index() {
        return "Hello World";
    }
	

    @RequestMapping("getUser")
    @ResponseBody
    public String getUser(){
        return String.valueOf(userService.getId());
    }

    @RequestMapping("getUsername")
    @ResponseBody
    public String getUsername(){
        return String.valueOf(userService.getUsername());
    }
    
    
    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser() {
    	Map<String, Object> map = new HashMap<String, Object>();
    	for(int i=100; i < 220;i++) {
    		map.put("id", i);
    		map.put("age", i-50);
    		userService.insertUser(map);    		
    	}
    	return "Hello World";
    }
    
}
