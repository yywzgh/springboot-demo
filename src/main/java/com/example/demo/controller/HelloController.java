package com.example.demo.controller;

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

    
}
